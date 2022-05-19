import java.util.ArrayList;
import java.util.List;

//승객이 입장하는 메소드를 인터페이스로 만들어서 각자 다른동작을 하게끔 해보았다.

interface passengerIn{
    void passengerIn();
}
class Bus implements passengerIn{
    int maxPassenger;
    int curPassenger;
    int price;
    int busNum;
    int fuelVolume;
    int speed;
    boolean status;

    //Bus클래스의 생성자
    Bus(int busNum , int fuelVolume,int speed,boolean status,int curPassenger ,int maxPassenger, int price){
        this.busNum = busNum;
        this.fuelVolume = fuelVolume;
        this.speed = speed;
        //status가 true인것은 운행을 의미
        this.status = true;
        this.curPassenger = curPassenger;
        this.maxPassenger = maxPassenger;
        this.price = price;
    }
    @Override
    public void passengerIn() {
        //현재 승객수가 최대승객수와 같거나
        if(this.curPassenger == this.maxPassenger) return;
            //status가 차고지행인 상태에서는 승객수를 증가시키지 않는다.
        else if(this.status == false) return;
            //그 이외의 경우에만 승객수가 증가한다.
        else this.curPassenger++;
    }

    //버스 상태 변경
    public void changeStatus(){
        if(this.fuelVolume < 10)
        {
            //주유량이 기준치에 못미치면 메세지를 print하고 운행상태를 차고지행으로 변경
            status = false;
            //주유가 필요합니다. 메세지 출력
            System.out.println("주유가 필요합니다.");
        }
        //주유 조건을 만족햇을시 버스상태변경 운행중이면 차고지행 , 차고지행이면 운행으로 변경
        this.status = true ? false : true;
    }
    //승객 탑승

    //속도 변경
    public void changeSpeed(int speed)
    {
        if(this.fuelVolume < 10)
        {
            //주유량이 기준치에 못미치면 메세지를 print하고 운행상태를 차고지행으로 변경
            //기준치는 임의로 10이라 설정
            status = false;
            System.out.println("주유량을 확인해 주세요.");
        }
        //변경하고싶은 speed값을 입력받아 속도변경
        this.speed = speed;
    }

    public void runBus(){
        //status의 true는 운행을 의미 false는 차고지행
        if(!(this.fuelVolume < 10)) status = true;
    }
    public void driveOver(){
        //운행 종료시 차고지행
        this.status = false;
    }
}


class Taxi implements passengerIn{
    //택시번호
    int taxiNum;
    //주유량
    int fuelValue;
    //현재속도
    int speed;
    //목적지
    String destination;
    //기본 거리
    double normalDistance;
    //목적지까지 거리
    double distanceToDestination;
    //기본요금
    int normalPrice;
    //거리당 요금 추가
    int pricePerDistance;
    //status true = 운행 중 , false 일반
    boolean status;

    public Taxi(int taxiNum, int fuelValue, int speed, String destination, double normalDistance, double distanceToDestination, int normalPrice, int pricePerDistance, boolean status) {
        this.taxiNum = taxiNum;
        this.fuelValue = fuelValue;
        this.speed = speed;
        this.destination = destination;
        this.normalDistance = normalDistance;
        this.distanceToDestination = distanceToDestination;
        this.normalPrice = normalPrice;
        this.pricePerDistance = pricePerDistance;
        //초기상태는 일반
        this.status = false;
    }

    //운행시작 기능조건 : 주유량 10이상
    public void runTaxi(){
        if(!(this.fuelValue < 10)) status = true;
    }
    //승객탑승
    @Override
    public void passengerIn(){
        //택시상태가 일반일때만 입장가능 및 상태를 운행중으로 변경
        if(!status) status = true;
        else System.out.println("탑승이 불가능합니다.");
    }
    //속도 변경
    public void changeSpeed(int speed){
        //현재속도보다 느린경우
        if(this.speed > speed){
            this.speed -= speed;
        }
        //현재속도보다 빠른경우
        else{
            this.speed += speed;
        }
    }
    public int totalPrice(){
        //- 기본 거리보다 먼 곳은 추가 요금이 붙습니다.
        //- 기본 거리와 추가 요금은 자유롭게 산정해 주세요
        int total = 0;
        double addDistance = distanceToDestination - normalDistance;
        //(기본요금 * 기본거리) + (추가요금 * (목적지거리-기본거리))
        total = (int)(normalPrice * normalDistance) + (int)(pricePerDistance * addDistance);
        System.out.println("요금은" + total + "원입니다.");
        return total;
    }

}
public class Main {
    public static void main(String[] args) {
        List<Integer> numOfBus = new ArrayList<>();
        List<Integer> numOfTaxi = new ArrayList<>();
        //버스 첫 생성시 리스트0번 인덱스에 있는 1을 가져오고 그 후부터는 1씩 증가한 값을 리스트에 넣게할 예정
        numOfBus.add(1);
        //택시도 동일
        numOfTaxi.add(1);
    }
}