package com.example.github.com.codingtest.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class WordChainInEnglish {

    public static void main(String[] args) {
        int n =2 ;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(solution(n, words)));
    }

    public static int[] solution(int n, String[] words) {
        // 결과 값을 0,0으로 초기화
        int[] answer = new int[]{0, 0};

        // 이전 단어를 저장할 변수
        String previousWord = words[0];
        // 말한 단어를 저장하는 ArrayList 초기화
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(previousWord);

        // 단어들을 순회
        for (int i = 1; i < words.length; i++) {
            // 현재 단어의 첫 번째 문자가 이전 단어의 마지막 문자와 다른 경우
            // 혹은 이전에 등장한 단어를 다시 말한 경우
            if (previousWord.charAt(previousWord.length() - 1) != words[i].charAt(0) || wordList.contains(words[i])) {
                // 탈락자가 생기므로 해당 정보를 배열에 저장하고 반환
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                return answer;
            }

            // 현재 단어를 이전 단어로 설정하고
            // 현재 단어를 말한 단어 목록에 추가
            previousWord = words[i];
            wordList.add(previousWord);
        }

        // 모든 단어를 순회했을 때 까지 탈락자가 생기지 않은 경우 [0, 0] 반환
        return answer;
    }

    //내가 푼 방식 과도하게 작성했다 하나 더배웟다.
    public static int[] prevSolution(int n, String[] words) {
        int[] answer = {0, 0};
        int thisUser = 1;
        String beforeWord = "-1";
        Map<Integer, ArrayList<String>> userWords = new HashMap<>();

        for (int loop = 0; loop < words.length; loop++) {
            //이번에 꺼낸 단어;
            String word = words[loop];
            ArrayList<String> list = userWords.get(thisUser);
            if (list == null) {
                list = new ArrayList<>();
            }

            //1. 1글자인지 확인
            if (word.length() == 1) {
                answer = new int[]{0, 0};
            }

            //2. 이전에 나왔던 단어인지 확인
            for (ArrayList<String> value : userWords.values()) {
                //유저와 해당 유저가 말한 단어 갯수를 추출
                //이전에 나온 해당 유저가 사용
                if (value.contains(word)) {
                    answer = new int[]{thisUser, list.size() + 1};
                }
            }
            //이전에 나온 단어일 경우 지금 한 단어의 갯수

            if (!beforeWord.equals("-1")) {
                char beforeLastWord = beforeWord.charAt(beforeWord.length() - 1);
                char thisFirstWOrd = word.charAt(0);
                //3. 이전 단어의 마지막 글자와 현재 단어의 첫 글자가 같은지 확인
                if (beforeLastWord != thisFirstWOrd) {
                    //4. 이전 단어의 마지막 글자와 현재 단어의 첫 글자가 다른 경우
                    answer = new int[]{thisUser, list.size() + 1};
                } else {
                    beforeWord = word;
                    //5. Set을 통해 단어 저장
                    list.add(word);
                    userWords.put(thisUser, list);
                }
            } else {
                beforeWord = word;
                list.add(word);
                userWords.put(thisUser, list);
            }

            // 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지
            if (thisUser == n) {
                thisUser = 1;
            } else {
                thisUser++;
            }
        }
        return answer;
    }
}
