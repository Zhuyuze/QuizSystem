package quizapp.quiz.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submission_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubmissionDetail {
    @Id
    @Column(name = "submission_detail_id")
    int id;
    @Column(name = "user_id")
    int userId;
    @Column(name = "submission_id")
    int submissionId;
    @Column(name = "question_id")
    int questionId;
    @Column(name = "user_choice")
    int userChoice;
}
