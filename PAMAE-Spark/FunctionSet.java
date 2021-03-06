package dmlab.main;

import java.util.ArrayList;
import java.util.List;

import scala.Tuple2;

public final class FunctionSet {
	/**
	 * euclidean distance function(l2-norm)
	 * @param object
	 * @param object
	 * @return distance
	 */
	public static float distance(FloatPoint object1, FloatPoint object2)
	{
		
		float result = 0.0f;
		for(int i=0; i < object1.getValues().length; i++)
		{	result += (object1.getValues()[i]-object2.getValues()[i]) * (object1.getValues()[i]-object2.getValues()[i]);}
		return (float)Math.sqrt(result);
	}

	/**
	 * calculate clustering error on one cluster
	 * @param cluster
	 * @param medoids
	 * @return clustering error
	 */
	public static float localClusteringError(ArrayList<FloatPoint> cluster, FloatPoint medoid)
	{
		float newCost = 0;
		for(int i=0; i<cluster.size(); i++)
		{	float dist = FunctionSet.distance(cluster.get(i), medoid);
			newCost += dist;}
		return newCost/(float)cluster.size();
	}	
	
	/**
	 * cacluate clustering error on all cluster
	 * @param dataSet
	 * @param medoids
	 * @param preCalcResult
	 * @return clustering error
	 */
	public static  float clusteringError(List<FloatPoint> dataSet, List<FloatPoint> medoids, float[][] preCalcResult) {
		float totalCost = 0;	
		for(FloatPoint point : dataSet) {
			float cost = Float.MAX_VALUE;	
			for(FloatPoint newMedoid : medoids) {
				float tempCost = preCalcResult[point.getDimension()][newMedoid.getDimension()];
				if(tempCost < cost)
					cost = tempCost;
			}
			totalCost += cost;
		}
		return totalCost;
	}
}
