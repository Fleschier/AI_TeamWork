package com.fleschier;

import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Created in Intellij IDEA
 * User: Fleschier
 * 2018-11-6
 */

public class UserCF {

    public static List<RecommendedItem> userCF(DataModel model, int userID, int RECOMMENDER_NUM) {
        //announce the recommendations for return. because in the try-catch part the value is unavailable outside
        List<RecommendedItem> recommendations = null;

        try {
            /*
            FileDataModel要求输入文件中的字段分隔符为逗号或者制表符，
            如果你想使用其他分隔符，你可以扩展一个FileDataModel的实现，
            例如，mahout中已经提供了一个解析MoiveLens的数据集（分隔符为::）的实现GroupLensDataModel。
             */

            //用户相似度，使用基于皮尔逊相关系数计算相似度
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            //选择邻居用户，使用NearestNUserNeighborhood实现UserNeighborhood接口，选择邻近的4个用户
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            //第一个参数是第几个用户,第二个参数是推荐几个物品
            recommendations = recommender.recommend(userID, RECOMMENDER_NUM);


        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}

