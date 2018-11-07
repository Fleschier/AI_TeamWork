package com.fleschier;

import org.apache.log4j.PropertyConfigurator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Fleschier
 * 2018-11-6
 */

public class Main {
    public static void main(String[] args) throws IOException{
        //add the fpllowing two lines and add log4j.properties to the root path of the project to avoid the warning
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        //这里的文件最多默认有三列属性
        File modelFile = new File("intro.csv");
        File modelFile1 = new File("../大数据测试用数据集/ml-latest-small-csv/rating.csv");
        File modelFile2 = new File("ratings.csv");
        File modelFile3 = new File("test.csv");

        int userID = 5;
        int RECOMMENDER_NUM = 3;

        DataModel model = new FileDataModel(modelFile3);
        List<RecommendedItem> USER_CF_recommendations = UserCF.userCF(model, userID, RECOMMENDER_NUM);
        List<RecommendedItem> ITEM_CF_recommendations = ItemCF.itemCF(model, userID, RECOMMENDER_NUM);
        List<RecommendedItem> SVD_recommendations = SVD_Reco.SVD_Recommener(model, userID, RECOMMENDER_NUM);

        System.out.println("UserID: " + userID);
        System.out.println("Now UserCF give " + RECOMMENDER_NUM + " recommend item(s): ");
        for (RecommendedItem recommendation : USER_CF_recommendations) {
            System.out.println(recommendation);
        }
        System.out.println("Now ItemCF give " + RECOMMENDER_NUM + " recommend item(s): ");
        for (RecommendedItem recommendation : ITEM_CF_recommendations) {
            System.out.println(recommendation);
        }
        System.out.println("Now SVD_Recommender give " + RECOMMENDER_NUM + " recommend item(s): ");
        for (RecommendedItem recommendation : SVD_recommendations) {
            System.out.println(recommendation);
        }

    }
}
