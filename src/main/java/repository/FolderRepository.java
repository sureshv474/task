package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import entity.FolderEntity;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    List<FolderEntity> findByParentId(Long parentId);

    @Transactional
    void deleteByIdOrParentId(Long id, Long parentId);

	void deleteById(Long id);

	Optional<FolderEntity> findById(Long folderId);

	FolderEntity save(FolderEntity folder);
}