package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    int id;
    @Column(name = "quiz_type")
    int type;
    @Column(name = "quiz_description")
    String description;
}
