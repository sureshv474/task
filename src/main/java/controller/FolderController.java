package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.FolderEntity;
import service.FolderService;

@RestController
@RequestMapping("/api/folders")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("/create")
    public Long createFolder(@RequestParam String folderName, @RequestParam Long parentId) {
        return folderService.createFolder(folderName, parentId);
    }

    @DeleteMapping("/delete/{folderId}")
    public void deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
    }

    @PostMapping("/createObject")
    public Long createObject(@RequestParam String objectName, @RequestParam Long parentId) {
        return folderService.createObject(objectName, parentId);
    }

    @GetMapping("/structure")
    public List<FolderEntity> getFolderStructure() {
        return folderService.getFolderStructure();
    }
}
