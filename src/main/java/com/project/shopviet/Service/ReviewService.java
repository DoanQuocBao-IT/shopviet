package com.project.shopviet.Service;

import com.project.shopviet.Model.Review;

public interface ReviewService {
    Review addReview(int orderItem_id,Review review);
    Review addReviewFeedback(int review_id, String feedback);
}
