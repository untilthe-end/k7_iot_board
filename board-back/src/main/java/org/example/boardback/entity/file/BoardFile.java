package org.example.boardback.entity.file;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardback.entity.board.Board;

/** 게시글(Board)과 파일(FileInfo)을 연결해주는 중간 테이블 */

@Entity
@Table(name = "board_files")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_board_files_board"))
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_board_files_file_info"))
    private FileInfo fileInfo;

    private Integer displayOrder;

    public void updateDisplayOrder(Integer newOrder) {
        this.displayOrder = newOrder;
    }

    public static BoardFile of(Board board, FileInfo fileInfo, Integer displayOrder) {
        return BoardFile.builder()
                .board(board)
                .fileInfo(fileInfo)
                .displayOrder(displayOrder)
                .build();
    }
}