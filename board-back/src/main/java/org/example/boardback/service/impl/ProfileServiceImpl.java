package org.example.boardback.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.boardback.entity.file.FileInfo;
import org.example.boardback.entity.user.User;
import org.example.boardback.repository.file.FileInfoRepository;
import org.example.boardback.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {

    private final UserRepository userRepository;
    private final FileServiceImpl fileService;
    private final FileInfoRepository fileInfoRepository;

    @Transactional
    public FileInfo updateProfile(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

        // 기존 프로필 삭제
        if (user.getProfileFile() != null) {
            // 기존 프로필 있는 경우
            FileInfo current = fileInfoRepository.findById(user.getProfileFile().getId())
                    .orElse(null); // 없으면 null 값 주기

            if (current != null) {
                fileService.deleteFile(current);
            }
        }

        // 새 파일 저장
        FileInfo saved = fileService.saveUserProfile(file);

        // User 테이블에 file_id 저장
        user.updateProfileImage(saved);
        userRepository.save(user);

        return saved;
    }
}
