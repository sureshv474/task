package entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "folder_entity")
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FolderEntity> subfolders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<FolderEntity> getSubfolders() {
		return subfolders;
	}

	public void setSubfolders(List<FolderEntity> subfolders) {
		this.subfolders = subfolders;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FolderEntity(Long id, String name, Long parentId, List<FolderEntity> subfolders) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.subfolders = subfolders;
	}

	public FolderEntity() {
		// TODO Auto-generated constructor stub
	}

    
}
