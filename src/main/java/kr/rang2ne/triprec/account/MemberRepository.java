package kr.rang2ne.triprec.account;

import kr.rang2ne.triprec.account.model.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rang on 2015-09-11.
 */
public interface MemberRepository extends CrudRepository<Member, String> {
}
