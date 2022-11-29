package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Submission {
    @Id
    @Column(name = "submission_id")
    int id;
    @Column(name = "user_id")
    int userId;
    @Column(name = "quiz_id")
    int quizId;
    @Column(name = "score")
    int score;
    @Column(name = "start_time")
    String startTime;
    @Column(name = "end_time")
    String endTime;
}
