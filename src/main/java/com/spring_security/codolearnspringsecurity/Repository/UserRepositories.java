package com.spring_security.codolearnspringsecurity.Repository;

import com.spring_security.codolearnspringsecurity.Entity.AppicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<AppicationUser, Integer> {

    /*Tu config method jpa*/

    Optional<AppicationUser> findByUsername(String username);
    /*Optional : duoc su dung de doi pho voi trung hop null
    neu co -> AppicationUser
    khong co -> tra ve Optional rong
    ===> khong can phai kiem tra null
    */

    /*
    Vi du :

    Optional<AppicationUser> userOptional = userRepository.findByUsername("someUsername");

    // Kiểm tra xem có tồn tại người dùng không
    if (userOptional.isPresent()) {
        AppicationUser user = userOptional.get();
        // Xử lý user
    } else {
        // Người dùng không tồn tại
    }

    ////////////////////////////

    String username = userRepository.findByUsername("someUsername")
                                .map(AppicationUser::getUsername)
                                .orElse("DefaultUsername");

    * */

}
