package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "all_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Option {
    @Id
    @Column(name = "option_id")
    int id;
    @Column(name = "question_id")
    int questionId;
    @Column(name = "option_description")
    String description;
    @Column(name = "is_correct")
    boolean isCorrect;
}
