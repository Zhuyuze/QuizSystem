package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    @Id
    @Column(name = "feedback_id")
    int id;
    @Column(name = "feedback_comment")
    String comment;
    @Column(name = "feedback_rating")
    int rating;
}
