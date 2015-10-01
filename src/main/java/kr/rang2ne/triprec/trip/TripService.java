package kr.rang2ne.triprec.trip;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import kr.rang2ne.triprec.account.MemberRepository;
import kr.rang2ne.triprec.account.model.Member;
import kr.rang2ne.triprec.trip.metaTagFilter.MetaTagFilter;
import kr.rang2ne.triprec.trip.metaTagFilter.MetaTagFilterSelecter;
import kr.rang2ne.triprec.trip.model.MetaTag;
import kr.rang2ne.triprec.trip.model.Scene;
import kr.rang2ne.triprec.trip.model.Trip;
import kr.rang2ne.triprec.view.model.SceneDto;
import kr.rang2ne.triprec.view.model.TripDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rang on 2015-09-11.
 *
 */
@Service
public class TripService  {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void insert(TripDto.Create dto, Member member) throws Exception {
        Trip trip = modelMapper.map(dto, Trip.class);
        trip.setMember(member);
        trip.setRegTime(Calendar.getInstance().getTime());
        trip.setModTime(Calendar.getInstance().getTime());

        List<Scene> scenes = trip.getScenes();
        scenes.forEach(scene -> {
            scene.setTrip(trip);
            List<MetaTag> metaTags = scene.getMetaTags().parallelStream()
                    .filter(metaTag -> metaTag.getDescription().getBytes().length < 255)
                    .collect(Collectors.toList());
            scene.setMetaTags(metaTags);
            sceneRepository.save(scene);
        });
        tripRepository.save(trip);
    }

    @Transactional
    public void update(TripDto.Update dto, Member member) throws Exception {
        Trip trip = modelMapper.map(dto, Trip.class);
        Trip findedTrip = tripRepository.findOne(dto.getId());
        if(findedTrip != null) {
            sceneRepository.delete(findedTrip.getScenes());
        }
        Member findmember = memberRepository.findOne(trip.getMember().getId());
        trip.setMember(findmember);

        trip.setModTime(Calendar.getInstance().getTime());
        List<Scene> scenes = trip.getScenes();
        scenes.forEach(scene -> {
            scene.setTrip(trip);
            sceneRepository.save(scene);
        });
        tripRepository.save(trip);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Trip trip = new Trip();
        trip.setId(id);
        sceneRepository.deleteByTrip(trip);
        tripRepository.delete(id);
    }

    @Transactional
    public List<TripDto.SelectList> getTrips(String memberId) throws Exception {
        List<Trip> trips = tripRepository.findByMemberWith(memberId);

        List<TripDto.SelectList> selectList = new ArrayList<>();
        trips.forEach(trip -> {
            List<SceneDto.SelectList> sceneLists = new ArrayList<>();
            trip.getScenes().forEach(scene -> sceneLists.add(modelMapper.map(scene, SceneDto.SelectList.class)));
            TripDto.SelectList tripForSelected = modelMapper.map(trip, TripDto.SelectList.class);
            tripForSelected.setScenes(sceneLists);
            selectList.add(tripForSelected);
        });

        return selectList;
    }

    public SceneDto.SaveFile uploadTempFile(MultipartFile uploadFile) throws Exception {
        String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //TODO static value change properties
        String resourcePath = "/images/" + curDate + "/";

        SceneDto.SaveFile saveFile = new SceneDto.SaveFile();

        if(uploadFile != null && !uploadFile.isEmpty()){
            File targetFile = makeUploadFile(uploadFile);

            // Read Meta Info
            Map<String, MetaTag> metaTagMap = readMetaData(targetFile);
            String extension = getFileExtension(targetFile);
            MetaTagFilter metaTagFilter = MetaTagFilterSelecter.getMetaTagFilter(extension, metaTagMap);
            saveFile = modelMapper.map(metaTagFilter, SceneDto.SaveFile.class);
            saveFile.setPictureHeight(metaTagFilter.getPictureHeight());
            saveFile.setPictureWidth(metaTagFilter.getPictureWidth());

            // Return Model
            saveFile.setPicturePath(resourcePath + targetFile.getName());
            List<MetaTag> tagList = metaTagMap.entrySet().stream()
                    .map(stringMetaTagEntry -> metaTagMap.get(stringMetaTagEntry.getKey()))
                    .collect(Collectors.toList());
            saveFile.setMetaTags(tagList);
        }

        return saveFile;
    }

    private File makeUploadFile(MultipartFile uploadFile) throws Exception {
        String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //TODO static value change properties
        String absolutePath = "D:/develop/sources/myWork/TripRec/src/main/resources/static";
        String resourcePath = "/images/" + curDate + "/";
        String newFileName = System.nanoTime()+"_"+uploadFile.getOriginalFilename().replaceAll(" ", "_");

        // Make Directory
        File directory = new File(absolutePath + resourcePath);
        if(!(directory.exists() && directory.isDirectory())) {
            directory.mkdir();
        }

        // Make File
        String uploadFilePath = absolutePath + resourcePath + newFileName;
        File targetFile = new File(uploadFilePath);
        uploadFile.transferTo(targetFile);
        return targetFile;
    }

    private Map<String, MetaTag> readMetaData(File imageFile) throws Exception {
        Map<String, MetaTag> metaTagMap = new HashMap<>();
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
        for (Directory directory : metadata.getDirectories()) {
            directory.getTags().forEach(tag -> {
                if(tag.getDescription().getBytes().length < 255) {
                    MetaTag metaTag = modelMapper.map(tag, MetaTag.class);
                    metaTag.setDirectoryName(directory.getName());
                    metaTagMap.put(metaTag.getDirectoryName()+"-"+metaTag.getTagType(), metaTag);
                }
            });
        }
        return metaTagMap;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
