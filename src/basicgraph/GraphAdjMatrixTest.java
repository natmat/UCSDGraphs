
package basicgraph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GraphAdjMatrixTest {

	@Test
	public void testEmptyGraph() {
		GraphAdjMatrix gam = new GraphAdjMatrix();
		List<Integer> ds = gam.degreeSequence();
		assertTrue(ds.size() == 0);
	}

	@Test
	public void testGetDistance2() {
		GraphAdjMatrix gam = new GraphAdjMatrix();
		List<Integer> twoHop;

		// Test matrix
		int[][] arr = {{1,0,0},{0,1,0},{0,0,1}};		
		setupArray(gam, arr);		

		{
			twoHop = gam.getDistance2(1);
			printData(twoHop);
			int[] expecteds = {1};
			int[] actuals = twoHop.stream().mapToInt(Integer::intValue).toArray();

			assertArrayEquals(expecteds, actuals);
		}
	}

	private void setupArray(GraphAdjMatrix gam, final int[][] in) {
		for (int i = 0 ; i < in.length ; i++) {
			gam.addVertex();
		}
		for (int i=0 ; i < in.length ; i++) {
			for (int j=0 ; j < in.length ; j++) {
				if (in[i][j] != 0) {
					//					System.out.println("Add "+i+","+j);
					gam.addEdge(i, j);
				}
			}
		}
	}

	@Test
	public void test4V1E() {
		GraphAdjMatrix gam = new GraphAdjMatrix();
		List<Integer> v = new ArrayList<Integer>(10);
		for (int i = 0 ; i < 4 ; i++) {
			v.add(gam.addVertex());
		}
		gam.addEdge(0, 1);
		List<Integer> ds = gam.degreeSequence();
		int[] expecteds = {1,0,0,0};
		assertArrayEquals(expecteds, toIntArray(ds));
	}

	int[] toIntArray(List<Integer> intList)  {
		int[] ret = new int[intList.size()];
		int i = 0;
		for (Integer e : intList)  
			ret[i++] = e.intValue();
		return ret;
	}

	void printData(int[] in) {
		for (int i = 0 ; i < in.length ; i++) {
			System.out.print(in[i] + " ");
		}
		System.out.println("\n");
	}

	void printData(List<Integer> in) {
		for (Integer i : in) {
			System.out.print(i + " ");
		}
		System.out.println("\n");
	}

	void printData(int[][] in) {
		for (int i = 0 ; i < in.length ; i++) {
			for (int j = 0 ; j < in.length ; j++) {
				System.out.print(in[i][j] + " ");
			}
			System.out.println("\n");
		}
	}
}

