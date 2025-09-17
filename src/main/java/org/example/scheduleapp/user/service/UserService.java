package org.example.scheduleapp.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.common.config.PasswordEncoder;
import org.example.scheduleapp.user.dto.UserResponseDto;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional // 내 정보 조회
    public UserResponseDto getMe(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional // 내 정보 수정
    public UserResponseDto updateUser(Long id, String inputPassword) {

        String encodedPassword = passwordEncoder.encode(inputPassword);

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        user.updateUser(encodedPassword);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Transactional
    public void deleteUser(Long id, String inputPassword) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }

        // 실제 삭제 대신 상태만 변경
        user.setDeleted(true);
        user.setUsername("(Deleted User)");

        userRepository.save(user);

        /*
            1. user 테이블에 있는 유저를 지우려고 했지만 그 유저를 참조하는 comment.user_id가 남아있어서 삭제를 막음
            2. 댓글은 남겨두고 유저만 삭제하고 싶기 때문에 User 엔티티를 soft delete 처리 한다. (실제로 삭제하지 않고 상태만 변경)
            3. User 엔티티에 탈퇴 여부를 표시할 boolean 필드를 만들어 준다.
            4. setUsername, setDeleted -> Setter를 만들어 준다.
            5. setDeleted(true) 상태가 확인되면,
            6. serUsername을 '탈퇴한 사용자'로 바꿔준 뒤,
            7. delete 메서드가 아닌 save 메서드로 user를 저장한다. (DB에서 실제 삭제가 아닌 논리적 삭제를 해야 하기 때문)
         */
    }
}
