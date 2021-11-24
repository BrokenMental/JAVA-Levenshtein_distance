package test;

//참조 : https://en.wikipedia.org/wiki/Levenshtein_distance
public class Levenshtein {
	public static int distance(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		
		int [] rowCosts = new int [str2.length() + 1];

		for (int j = 0; j < rowCosts.length; j++) {
			rowCosts[j] = j;
		}
		
		//i는 행(|, 세로에 위치한 문자), j는 열(ㅡ, 가로에 위치한 문자)
		//첫번째는 뛰어넘고(이미 0이니까) a의 개수만큼 반복
		for (int i = 1; i <= str1.length(); i++) {
			rowCosts[0] = i;
			
			//바로 좌측 값(맨처음엔 비교할 행의 위치, i의 위치가 매번 재귀마다 변함)
			int beforeVal = i - 1;
			
			for (int j = 1; j <= str2.length(); j++) {
				//첫 시작 : rowCosts[j-1]의 값이 이미 이번행 첫번째 열의 값으로 덮어 씌워졌기 때문에 이번 행의 값과 이전 행의 동일한열의 값을 비교
				//두번째부터 : rowCosts[j-1]의 값이 이미 이번행의 값으로 덮어 씌워졌기 때문에 이번 행의 값과 이전 행의 동일한열의 값을 비교
				int minFirst = Math.min(rowCosts[j], rowCosts[j - 1]) + 1;
				
				//현재 비교하는 행의 문자와 열의 문자가 같으면 좌측 대각선 상단(이전 행의 수)를 가져오고 같지 않으면 이전행의 수에 +1
				int minSecond = str1.charAt(i - 1) == str2.charAt(j - 1)? beforeVal : (beforeVal + 1);
				
				//최종적으로 좌, 상단, 대각선 좌측 상단 값을 비교해서 가장 낮은 값
				int resultVal = Math.min(minFirst,  minSecond);
				
				//현재값 이 다음 실행엔 이전(좌측) 값이 됨
				beforeVal = rowCosts[j];
				
				//현재값에 결과값 입력
				rowCosts[j] = resultVal;
				System.out.print(resultVal + " ");
			}
			
			System.out.println();
		}
		
		return rowCosts[str2.length()];
	}
	
	public static void main(String [] args) {
		String [] data = { "sitting", "kitten", "saturday", "sunday", "rosettacode", "raisethysword" };
		
		for (int i = 0; i < data.length; i += 2) {
			System.out.println("distance(" + data[i] + ", " + data[i+1] + ") = " + distance(data[i], data[i+1])+"\n");
		}
	}
}

