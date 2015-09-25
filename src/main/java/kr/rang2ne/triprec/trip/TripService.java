package kr.rang2ne.triprec.trip;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import kr.rang2ne.triprec.account.MemberRepository;
import kr.rang2ne.triprec.account.model.Member;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rang on 2015-09-11.
 *
 */
@Service
@Transactional
public class TripService  {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void insert(TripDto.Create dto, Member member) throws Exception {
        Trip trip = modelMapper.map(dto, Trip.class);
        trip.setMember(member);
        trip.setRegTime(Calendar.getInstance().getTime());
        trip.setModTime(Calendar.getInstance().getTime());

        List<Scene> scenes = trip.getScenes();
        scenes.forEach(scene -> {
            scene.setTrip(trip);
            sceneRepository.save(scene);
        });
        tripRepository.save(trip);
    }

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

    public void delete(Long id) throws Exception {
        Trip trip = new Trip();
        trip.setId(id);
        sceneRepository.deleteByTrip(trip);
        tripRepository.delete(id);
    }

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
        SceneDto.SaveFile saveFile = new SceneDto.SaveFile();

        if(uploadFile != null && !uploadFile.isEmpty()){

            String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //TODO static value change properties
            String absolutePath = "D:/develop/sources/myWork/TripRec/src/main/resources";
            String resourcePath = "/static/images/" + curDate + "/";

            // Make Directory
            File directory = new File(absolutePath + resourcePath);
            if(!(directory.exists() && directory.isDirectory())) {
                directory.mkdir();
            }

            // Make File
            String newFileName = System.nanoTime()+"_"+uploadFile.getOriginalFilename().replaceAll(" ", "_");
            String uploadFilePath = absolutePath + resourcePath + newFileName;
            File targetFile = new File(uploadFilePath);
            uploadFile.transferTo(targetFile);

            // Read Meta Info
            List<MetaTag> metaTags = readMetaData(targetFile);

            // Return Model
            saveFile.setPicturePath(resourcePath + newFileName);
            saveFile.setMetaTags(metaTags);

        }

        return saveFile;
    }

    private List<MetaTag> readMetaData(File imageFile) throws Exception {
        List<MetaTag> metaTags = new ArrayList<>();
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
        for (Directory directory : metadata.getDirectories()) {
            directory.getTags().forEach(tag -> {
                MetaTag metaTag = modelMapper.map(tag, MetaTag.class);
                metaTag.setDirectoryName(directory.getName());
                metaTags.add(metaTag);
            });
        }
        return metaTags;
    }
}
