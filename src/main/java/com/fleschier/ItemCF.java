package com.fleschier;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Fleschier
 * 2018-11-6
 */

public class ItemCF {
    public static List<RecommendedItem> itemCF(DataModel model, int userID, int RECOMMENDER_NUM) {

        List<RecommendedItem> recommendations = null;
        try {
            //物品相似度，使用基于皮尔逊相关系数计算相似度
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

            Recommender recommender1 = new GenericItemBasedRecommender(model, similarity);

            //第一个参数是第几个用户,第二个参数是推荐几个物品
            recommendations = recommender1.recommend(userID, RECOMMENDER_NUM);

        }  catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}
