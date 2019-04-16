/*
 *
 * 클래스 이름 : RacingGame.java
 *
 * 버전 정보 : 1.0.0
 *
 * 날짜 : 2019.04.04
 *
 * 작성자 : 483759
 * */

package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RacingGame {
  private static int carNum;
  private static int tryNum;
  /*
   * Car 객체에 접근하여 게임을 진행하는 클래스
   * */

  public static String[] InputCarName() {
    /*
     * 차의 이름을 입력받는 메소드
     * 문자열을 입력받아 ,(콤마)를 기준으로 분리하여 문자열 배열 반환
     * */
    Scanner sc = new Scanner(System.in);
    System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,)으로 구분)");
    String Input_str = sc.nextLine();
    String[] carName = Input_str.split(",");

    return carName;
  }

  public static Car CreateCarObject(String carName) {
    /*
     * 차의 이름을 인자로 받아 Car 인스턴스를 생성하여 반환하는 메소드
     * */
    Car carObject = new Car(carName);
    return carObject;
  }

  public static List<Car> CreateCarList(String[] carName) {
    /*
     * 저장된 차 이름에 대한 String 배열을 인자로 받아와
     * Car 인스턴스 배열을 반환하는 메소드
     * */
    List<Car> carList = new ArrayList<Car>();
    for (int i = 0; i < carName.length; i++) {
      carList.add(CreateCarObject(carName[i]));
    }
    return carList;
  }

  public static int InputTryNumber() {
    /*
     * 경기 횟수를 입력받아 반환하는 메소드
     * */
    Scanner sc = new Scanner(System.in);
    System.out.println("시도할 회수는 몇회인가요?");
    int tryNum = sc.nextInt();
    return tryNum;
  }

  public static void CarRacing(List<Car> carList, int carNum) {
    /*
     * Car 배열과 숫자르 인자로 받아 각각의 경기를 진행하는 메소드
     * */
    for (int i = 0; i < carNum; i++) {
      /* 각각의 차량에 랜덤 정수를 발생시켜 위치를 계산하고 현재 위치를 출력 */
      int random = carList.get(i).RandomMove();
      carList.get(i).CalculateLocation(random);
      PrintCarMove(carList.get(i));
    }
  }

  public static void PrintCarMove(Car racingCar) {
    /*
     * 차량 인스턴스를 인자로 받아 현재 위치를 출력하는 메소드
     * */
    System.out.print(racingCar.getCarName() + ":");
    for (int i = 0; i < racingCar.getCarPosition(); i++) {
      System.out.print("-");
    }
    System.out.println();
  }

  public static void StartGame(List<Car> carList, int carNum, int tryNum) {
    /*
     * 정해진 경기횟수에 따라 게임을 진행하는 메소드
     * */
    for (int i = 0; i < tryNum; i++) {
      CarRacing(carList, carNum);
      System.out.println();
    }
  }

  public static void WhoseCarWin(List<Car> carList, int carNum) {
    /*
     * 경기가 끝난 후 이긴 차량의 목록을 계산하는 메소드
     * */
    int max = 0;  //최대 위치 저장 변수
    int idx = 0;  //이긴 차량의 숫자 저장 변수
    Car[] winnerList = new Car[10];
    for (int i = 0; i < carNum; i++) {
      if (carList.get(i).getCarPosition() > max) {
        /* 최대 위치를 max 변수에 저장 */
        max = carList.get(i).getCarPosition();
      }
    }
    for (int i = 0; i < carNum; i++) {
      if (carList.get(i).getCarPosition() == max) {
        /*이긴 차량의 목록을 winnerList에 저장*/
        winnerList[idx++] = carList.get(i);
      }
    }
    PrintWinCar(winnerList, idx);
  }

  public static void PrintWinCar(Car[] winnerList, int winNum) {
    //String result = String.join(",)
    for (int i = 0; i < winNum - 1; i++) {
      System.out.print(winnerList[i].getCarName() + ", ");
    }
    System.out.print(winnerList[winNum - 1].getCarName());
    System.out.println("가 최종 우승했습니다.");
  }

  public static void main(String args[]) {
    String[] carName = InputCarName(); //차량의 목록을 저장하는 문자열 배열
    carNum = carName.length;  //차량의 개수 저장 변수
    List<Car> carList = CreateCarList(carName);  //차량 인스턴스 저장 배열
    tryNum = InputTryNumber();  //경기 횟수 저장
    StartGame(carList, carNum, tryNum); //경기 횟수에 따른 게임 진행
    WhoseCarWin(carList, carNum); //이긴 차량 이름 출력
  }
}
