import java.util.ArrayList;
import java.util.List;

//승객이 입장하는 메소드를 인터페이스로 만들어서 각자 다른동작을 하게끔 해보았다.


abstract class vehicle{
    int num;
    int fuelVolume;
    int speed;
    int maxPassenger;
    boolean status;
    abstract public void run();
    abstract public void changeStatus();
    abstract public void passengerIn(int passenger);
    abstract public void changeFuel(int Fuel);



}
class Bus extends vehicle{

    int curPassenger;
    int price;
    int maxPrice;
    List<Integer> busList = new ArrayList<>();
    //버스번호 중복하는지 감시하는 메소드
    private boolean checkBusNum(int busnum){
        //버스를 체크해서 중복이 있으면 fasle리턴
        for(int i=0; i<busList.size(); i++){
            if(busnum == busList.get(i))
            {
                System.out.println("버스번호가 중복입니다.");
                System.out.println("---------------------");
                return false;
            }
        }
        busList.add(busnum);
        return true;
    }
    //Bus클래스의 생성자
    Bus(int busNum , int fuelVolume,int speed,boolean status,int curPassenger ,int maxPassenger, int price){
        if(!checkBusNum(busNum)) {
            return;
        }
        else
        {
            System.out.println("버스 번호" + busNum + "이 생성 되었습니다.");
            System.out.println("---------------------");
        }
        this.fuelVolume = fuelVolume;
        this.speed = speed;
        //status가 true인것은 운행을 의미
        this.status = true;
        this.curPassenger = 0;
        this.maxPassenger = maxPassenger;
        this.price = price;
        //버스 생성자 만들때 탑승한 고객수 * price가 초기돈
        this.maxPrice = 0;
        passengerIn(curPassenger);
        changeStatus();
    }
    public void changeFuel(int Fuel){
        fuelVolume += Fuel;
        changeStatus();
        if(!status)
            System.out.println("상태 = 차고지행");
        else System.out.println("상태 = 운행중");
        System.out.println("현재 주유량 = " + fuelVolume);
        System.out.println("---------------------");

    }
    public void passengerIn(int passenger) {
        //현재 승객수가 최대승객수와 같거나
        if((this.curPassenger + passenger) > this.maxPassenger)
        {
            System.out.println("최대 승객수를 초과하였습니다!");
            System.out.println("---------------------");
            return;
        }
        //status가 차고지행인 상태에서는 승객수를 증가시키지 않는다.
        else if(this.status == false)
        {
            System.out.println("차를 운행할 수 없는 상태입니다!");
            System.out.println("---------------------");
            return;
        }
            //그 이외의 경우에만 승객수가 증가한다.
        else
        {
            //price*탑승한 고객수 만큼 번 요금 증가
            maxPrice += price * passenger;
            //현재 승객수 passenger만큼 증가
            this.curPassenger += passenger;
            System.out.println("탑승 승객수 =" + this.curPassenger);
            System.out.println("잔여 승객수 =" + (maxPassenger - this.curPassenger));
            System.out.println("요금확인 =" + maxPrice);
            System.out.println("---------------------");
        }
    }

    //버스 상태 변경
    public void changeStatus(){
        //운행중이였으면 조건없이 차고지행으로
        //주유량 검사
        if(this.fuelVolume < 10)
        {
            //주유량이 기준치에 못미치면 메세지를 print하고 운행상태를 차고지행으로 변경
            status = false;
            //주유가 필요합니다. 메세지 출력
            System.out.println("주유가 필요합니다.");
            System.out.println("운행을 정지합니다.");
            System.out.println("---------------------");
            return;
        }
        //조건을 만족하면 운행중으로 변경
        else this.status = true;

    }
    //승객 탑승
    //속도 변경
    public void changeSpeed(int speed)
    {
        if(this.fuelVolume < 10)
        {
            //주유량이 기준치에 못미치면 메세지를 print하고 운행상태를 차고지행으로 변경
            //기준치는 임의로 10이라 설정
            System.out.println("주유량을 확인해 주세요.");
            System.out.println("---------------------");
            driveOver();
        }
        //변경하고싶은 speed값을 입력받아 속도변경
        this.speed = speed;
    }

    public void run(){
        //status의 true는 운행을 의미 false는 차고지행
        if(!(this.fuelVolume < 10)) status = true;
    }
    public void driveOver(){
        //운행 종료시 차고지행
        this.curPassenger = 0;
        this.maxPrice = 0;
        System.out.println("운행을 정지합니다.");
        System.out.println("---------------------");
        this.status = false;
    }
}


class Taxi extends vehicle{
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
    List<Integer> taxiList = new ArrayList<>();
    private boolean checkBusNum(int busnum){
        //버스를 체크해서 중복이 있으면 fasle리턴
        for(int i=0; i<taxiList.size(); i++){
            if(busnum == taxiList.get(i))
            {
                System.out.println("택시번호가 중복입니다.");
                System.out.println("---------------------");
                return false;
            }
        }
        taxiList.add(busnum);
        return true;
    }
    public Taxi(int taxiNum, int fuelValue, int speed, String destination, double normalDistance, double distanceToDestination, int normalPrice, int pricePerDistance, boolean status) {
        if(!checkBusNum(taxiNum)) {
            return;
        }
        else {
            System.out.println("택시 번호" + taxiNum + "이 생성 되었습니다.");
            System.out.println("---------------------");
        }
        this.fuelVolume = fuelValue;
        this.speed = speed;
        this.destination = destination;
        this.normalDistance = normalDistance;
        this.distanceToDestination = distanceToDestination;
        this.normalPrice = normalPrice;
        this.pricePerDistance = pricePerDistance;
        //초기상태는 일반
        this.status = false;
    }
    public void changeFuel(int Fuel){
        fuelVolume += Fuel;
        if(!status)
            System.out.println("상태 = 연료부족으로 인한 일반상태 전환");
        else System.out.println("상태 = 연료 문제없음");
        System.out.println("현재 주유량 = " + fuelVolume);
        System.out.println("---------------------");
    }
    public void changeStatus(){
        //운행중이였으면 조건없이 일반으로
        //주유량 검사
        if(this.fuelVolume < 10)
        {
            //주유량이 기준치에 못미치면 메세지를 print하고 운행상태를 차고지행으로 변경
            status = false;
            //주유가 필요합니다. 메세지 출력
            System.out.println("주유가 필요합니다.");
            System.out.println("운행을 정지합니다.");
            System.out.println("---------------------");
            return;
        }
    }
    public void driveOver(){
        System.out.println("운행을 정지합니다.");
        System.out.println("---------------------");
        this.status = false;
    }
    //운행시작 기능조건 : 주유량 10이상
    public void run()
    {
        if(!(this.fuelVolume < 10)) status = true;
    }
    //승객탑승
    public void passengerIn(int passenger){
        //택시상태가 일반일때만 입장가능 및 상태를 운행중으로 변경
        if(!status)
        {
            //운행중으로 상태변경
            status = true;
        }
        //운행중일땐 탑승불가
        else
        {
            System.out.println("탑승이 불가능합니다.");
            System.out.println("---------------------");
        }
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
        System.out.println("---------------------");
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

        Bus bus1 = new Bus(1,100,0,true,5,30,1000);
        bus1.passengerIn(2);
        bus1.changeFuel(-50);
        bus1.driveOver();
        bus1.changeFuel(10);
        bus1.run();
        bus1.passengerIn(45);
        bus1.passengerIn(5);
        bus1.changeFuel(-55);
    }
}