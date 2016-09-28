package gm.connectfour.domain.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@EqualsAndHashCode
public class Game {
    private String id;
    private Board board;
    private Player lastColour;
    private boolean deleted = false;
    private GameState state;
    //TODO: unique identifiers per player? so that another player cannot move in this game

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    public Player getLastColour() {
        return lastColour;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Enumerated(EnumType.STRING)
    public GameState getState() {
        return state;
    }

    @Column(name = "board", nullable = false)
    private String getStrBoard() {
        return board==null ? null : board.toString();
    }

    @Transient
    public Board getBoard() {
        return board;
    }

    private void setId(String id) {
        this.id = id;
    }

    public void setBoard(Board board) {
        this.board = board;
        setStrBoard(board==null?null:board.toString());
    }

    private void setStrBoard(String strBoard) {
        this.board = Board.builder().build(strBoard);
    }

    public void setLastColour(Player lastColour) {
        this.lastColour = lastColour;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
