package com.fleschier;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;

/**
 * Created in Intellij IDEA
 * User: Fleschier
 * 2018-11-6
 */

public class SVD_Reco {
    public static List<RecommendedItem> SVD_Recommener(DataModel model, int userID, int RECOMMENDER_NUM){
        List<RecommendedItem> recommendations = null;

        try {
            Factorizer fatorizer = new ImplicitLinearRegressionFactorizer(model);
            SVDRecommender recommender = new SVDRecommender(model, fatorizer);

            recommendations = recommender.recommend(userID, RECOMMENDER_NUM);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}
