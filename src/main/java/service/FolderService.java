package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.FolderEntity;
import repository.FolderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public Long createFolder(String folderName, Long parentId) {
        FolderEntity folder = new FolderEntity();
        folder.setName(folderName);
        folder.setParentId(parentId);
        return folderRepository.save(folder).getId();
    }

    public void deleteFolder(Long folderId) {
        Optional<FolderEntity> optionalFolder = folderRepository.findById(folderId);

        optionalFolder.ifPresent(folderToDelete -> {
            List<FolderEntity> subfolders = getAllSubfolders(folderToDelete.getId());
            subfolders.add(folderToDelete);

            for (FolderEntity folder : subfolders) {
                folderRepository.deleteById(folder.getId());
            }
        });
    }
    public Long createObject(String objectName, Long parentId) {
        FolderEntity object = new FolderEntity();
        object.setName(objectName);
        object.setParentId(parentId);
        return folderRepository.save(object).getId();
    }

    public List<FolderEntity> getFolderStructure() {
        List<FolderEntity> rootFolders = folderRepository.findByParentId(null);
        List<FolderEntity> result = new ArrayList<>();

        for (FolderEntity rootFolder : rootFolders) {
            rootFolder.setSubfolders(getAllSubfolders(rootFolder.getId()));
            result.add(rootFolder);
        }

        return result;
    }

    private List<FolderEntity> getAllSubfolders(Long parentId) {
        List<FolderEntity> subfolders = folderRepository.findByParentId(parentId);
        for (FolderEntity subfolder : subfolders) {
            subfolder.setSubfolders(getAllSubfolders(subfolder.getId()));
        }
        return subfolders;
    }
}
