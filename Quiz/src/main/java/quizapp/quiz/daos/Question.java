package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    @Id
    @Column(name = "question_id")
    int id;
    @Column(name = "quiz_id")
    int quizId;
    @Column(name = "question_description")
    String description;
}
