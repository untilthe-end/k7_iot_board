package org.example.boardback.entity.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.boardback.entity.base.BaseTimeEntity;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(
        name = "board_categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_board_category_name", columnNames = "name")
        })

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 같은 패키지나 상속받는 클래스에서만
public class BoardCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Board> boards = new HashSet<>();

    @Builder
    public BoardCategory(String name) {
        this.name = name;
    }
}
