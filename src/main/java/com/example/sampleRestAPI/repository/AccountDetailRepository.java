package com.example.sampleRestAPI.repository;

import com.example.sampleRestAPI.entity.AccountDetail;
import com.example.sampleRestAPI.entity.AccountDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDetailRepository extends JpaRepository<AccountDetail, AccountDetailID> {

    //계좌번호의 max 입금순번 조회
    @Query("select max(ad.dpstSeq) from AccountDetail ad where ad.dpstDy = :dpstDy and ad.account.acno = :acno")
    Integer getMaxDpstSeqByDpstDyAndAcno(@Param("dpstDy") String dpstDy, @Param("acno") Long acno);

    //계좌번호로 계좌내역 조회
    @Query("select ad from AccountDetail ad where ad.account.acno = :acno")
    List<AccountDetail> getAccountDetailsByAccount(@Param("acno") Long acno);

    //사용자 계좌별 예치금 합계 조회
    @Query("select" +
            "   a.acno" +
            "   , sum(case ad.dpstWthdrwYn when 'Y' then ad.dpstAmt else 0 end)" +
            "       - sum(case ad.dpstWthdrwYn when 'N' then ad.dpstAmt else 0 end) " +
            " from User u" +
            "    join Account a on u = a.user" +
            "    join AccountDetail ad on a = ad.account" +
            " where u.userId = :userId" +
            " group by a"
    )
    List<Object[]> getUserAccountBalance(@Param("userId") Long userId);

    //연령대별 평균예치금 조회
    @Query(value =
            "select" +
            "   u.age/10*10" +
            "   , floor((sum(case ad.dpst_wthdrw_yn when 'Y' then ad.dpst_amt else 0 end)" +
            "       -sum(case ad.dpst_wthdrw_yn when 'N' then ad.dpst_amt else 0 end))" +
            "     / count(distinct user_id))" +
            " from user u" +
            "    join account a on u.user_id = a.user_user_id" +
            "    join account_detail ad on a.acno = ad.acno" +
            " group by u.age/10*10"
            , nativeQuery = true
    )
    List<Object[]> getUserAgeGroupAvgBalance();

    //입력년도 예치금합계 조회
    @Query("select" +
            "   (sum(case ad.dpstWthdrwYn when 'Y' then ad.dpstAmt else 0 end)" +
            "       - sum(case ad.dpstWthdrwYn when 'N' then ad.dpstAmt else 0 end))" +
            " from AccountDetail ad" +
            " where ad.dpstDy between concat(:year, '0101') and concat(:year, '1231')"
    )
    Object[] getYearTotBalance(@Param("year") String year);

    //입력기간 사용자별 예치금합계 조회
    @Query("select" +
            "   u" +
            "   , (sum(case ad.dpstWthdrwYn when 'Y' then ad.dpstAmt else 0 end)" +
            "       - sum(case ad.dpstWthdrwYn when 'N' then ad.dpstAmt else 0 end)) as balance" +
            " from User u" +
            "    join Account a on u = a.user" +
            "    join AccountDetail ad on a = ad.account" +
            " where ad.dpstDy between :strtDy and :endDy" +
            " group by u" +
            " order by balance desc"
    )
    List<Object[]> getUserBalanceDescList(@Param("strtDy") String strtDy, @Param("endDy") String endDy);

}
