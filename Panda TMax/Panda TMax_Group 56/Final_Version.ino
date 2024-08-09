
//libraries
#include <LiquidCrystal.h>    //for LCD display
#include <SoftwareSerial.h>  //for Blutooth connection
#include <IRremote.h>         //for infrared remote control 
#include <dht.h>              //for DHT detection
//define port
#define wheel1_pin1 22
#define wheel1_pin2 23
#define wheel1_pin3 24
#define wheel1_pin4 25
#define wheel2_pin1 5
#define wheel2_pin2 4
#define wheel2_pin3 3
#define wheel2_pin4 2
#define ray_sensor A0
#define ray_sensor_1  6
#define ray_sensor_2  7
#define trace1 47
#define trace2 45
#define trace3 43
#define RECV_PIN 50 
#define DHT11_PIN 51//温湿度传感器的串口
#define fish_light1 52//食人鱼灯感光的串口
#define fish_light2 53

LiquidCrystal lcd(31, 33, 35, 37, 39, 49);
IRrecv irrecv(RECV_PIN);
decode_results results;
//define the music
#define NTD0 -1
#define NTD1 294
#define NTD2 330
#define NTD3 350
#define NTD4 393
#define NTD5 441
#define NTD6 495
#define NTD7 556

#define NTDL1 147
#define NTDL2 165
#define NTDL3 175
#define NTDL4 196
#define NTDL5 221
#define NTDL6 248
#define NTDL7 278

#define NTDH1 589
#define NTDH2 661
#define NTDH3 700
#define NTDH4 786
#define NTDH5 882
#define NTDH6 990
#define NTDH7 112
//列出全部D调的频率
#define WHOLE 1
#define HALF 0.5
#define QUARTER 0.25
#define EIGHTH 0.25
#define SIXTEENTH 0.625
//列出所有节拍
int tunejoy[]=                 //根据简谱列出各频率
{
  NTD3,NTD3,NTD4,NTD5,
  NTD5,NTD4,NTD3,NTD2,
  NTD1,NTD1,NTD2,NTD3,
  NTD3,NTD2,NTD2,
  NTD3,NTD3,NTD4,NTD5,
  NTD5,NTD4,NTD3,NTD2,
  NTD1,NTD1,NTD2,NTD3,
  NTD2,NTD1,NTD1,
  NTD2,NTD2,NTD3,NTD1,
  NTD2,NTD3,NTD4,NTD3,NTD1,
  NTD2,NTD3,NTD4,NTD3,NTD2,
  NTD1,NTD2,NTDL5,NTD0,
  NTD3,NTD3,NTD4,NTD5,
  NTD5,NTD4,NTD3,NTD4,NTD2,
  NTD1,NTD1,NTD2,NTD3,
  NTD2,NTD1,NTD1
};
float durtjoy[]=                   //根据简谱列出各节拍
{
  1,1,1,1,
  1,1,1,1,
  1,1,1,1,
  1+0.5,0.5,1+1,
  1,1,1,1,
  1,1,1,1,
  1,1,1,1,
  1+0.5,0.5,1+1,
  1,1,1,1,
  1,0.5,0.5,1,1,
  1,0.5,0.5,1,1,
  1,1,1,1,
  1,1,1,1,
  1,1,1,0.5,0.5,
  1,1,1,1,
  1+0.5,0.5,1+1,
};
int tunesky[] =  
{  
 NTD0,NTD0,NTD0,NTD6,NTD7,NTDH1,NTD7,NTDH1,NTDH3,NTD7,NTD7,NTD7,NTD3,NTD3,
 NTD6,NTD5,NTD6,NTDH1,NTD5,NTD5,NTD5,NTD3,NTD4,NTD3,NTD4,NTDH1,
 NTD3,NTD3,NTD0,NTDH1,NTDH1,NTDH1,NTD7,NTD4,NTD4,NTD7,NTD7,NTD7,NTD0,NTD6,NTD7,
 NTDH1,NTD7,NTDH1,NTDH3,NTD7,NTD7,NTD7,NTD3,NTD3,NTD6,NTD5,NTD6,NTDH1,
 NTD5,NTD5,NTD5,NTD2,NTD3,NTD4,NTDH1,NTD7,NTD7,NTDH1,NTDH1,NTDH2,NTDH2,NTDH3,NTDH1,NTDH1,NTDH1,
 NTDH1,NTD7,NTD6,NTD6,NTD7,NTD5,NTD6,NTD6,NTD6,NTDH1,NTDH2,NTDH3,NTDH2,NTDH3,NTDH5,
 NTDH2,NTDH2,NTDH2,NTD5,NTD5,NTDH1,NTD7,NTDH1,NTDH3,NTDH3,NTDH3,NTDH3,NTDH3,
 NTD6,NTD7,NTDH1,NTD7,NTDH2,NTDH2,NTDH1,NTD5,NTD5,NTD5,NTDH4,NTDH3,NTDH2,NTDH1,
 NTDH3,NTDH3,NTDH3,NTDH3,NTDH6,NTDH6,NTDH5,NTDH5,NTDH3,NTDH2,NTDH1,NTDH1,NTD0,NTDH1,
 NTDH2,NTDH1,NTDH2,NTDH2,NTDH5,NTDH3,NTDH3,NTDH3,NTDH3,NTDH6,NTDH6,NTDH5,NTDH5,
 NTDH3,NTDH2,NTDH1,NTDH1,NTD0,NTDH1,NTDH2,NTDH1,NTDH2,NTDH2,NTD7,NTD6,NTD6,NTD6,NTD6,NTD7
};
float durtsky[]= 
{  
  1,1,1,0.5,0.5,     1+0.5,0.5,1,1,     1,1,1,0.5,0.5,
  1+0.5,0.5,1,1,     1,1,1,1,          1+0.5,0.5,1,1, 
  1,1,0.5,0.5,0.5,0.5,    1+0.5,0.5,1,1,     1,1,1,0.5,0.5,
  1+0.5,0.5,1,1,    1,1,1,0.5,0.5,     1+0.5,0.5,1,1,
  1,1,1,0.5,0.5,    1,0.5,0.25,0.25,0.25,0.5,    0.5,0.5,0.5,0.25,0.5,1,
  0.5,0.5,0.5,0.5,1,1,    1,1,1,0.5,0.5,    1+0.5,0.5,1,1,
  1,1,1,0.5,0.5,    1.5,0.5,1,1,    1,1,1,1,
  0.5,0.5,1,1,0.5,0.5,    1.5,0.25,0.5,1,    1,1,1,1,
  1,1,1,1,    1,1,1,1,    0.5,0.5,1,1,0.5,0.5,
  1,0.5,0.5,1,1,    1,1,1,1,    1,1,1,1,
  0.5,0.5,1,1,0.5,0.5,    1,0.5,0.25,0.5,1,    1,1,1,0.5,0.5
};
int tunetiger[]=
{
  NTD1,NTD2,NTD3,NTD1,
  NTD1,NTD2,NTD3,NTD1,
  NTD3,NTD4,NTD5,
  NTD3,NTD4,NTD5,
  NTD5,NTD6,NTD5,NTD4,NTD3,NTD1,
  NTD5,NTD6,NTD5,NTD4,NTD3,NTD1,
  NTD1,NTD5,NTD1,
  NTD1,NTD5,NTD1,
};
//音拍数组，每一行代表4拍
float durttiger[]=
{
  1,1,1,1,
  1,1,1,1,
  1,1,2,
  1,1,2,
  0.75,0.25,0.75,0.25,1,1,
  0.75,0.25,0.75,0.25,1,1,
  1,1,2,
  1,1,2
};

int length1,length2,length3;
int tonepin=8;   

//define variables
dht DHT;
long run_car = 0x00FF18E7;//按键2 按键定义
long back_car = 0x00FF4AB5;//按键8
long left_car = 0x00FF10EF;//按键4
long right_car = 0x00FF5AA5;//按键6
long stop_car = 0x00FF38C7;//按键5
long left_turn = 0x00FF42BD;//按键7
long right_turn = 0x00FF52AD;//按键9
int direction = 0;

int _step1 =0 ;
int stepperSpeed1 = 2;//电机转速,1ms一步
int _step2 =0 ;
int stepperSpeed2 = 2;//电机转速,1ms一步

float value_1 = 0.0;
float value_2 = 0.0;
float dist = 0.0;
int light = 10;
char direc;
int flag = 0;
char comm;

//function declaration
void Auto_Track();
void Ob_Avoid();
void IR_Control();
void Follow();
void Music_Play();
void Env_Monitor();
void Light_On();
void Light_Off();
void show_dist();
void show_time();
void Go();
void Back();
void Left();
void Right();
void Clockwise();
void Counterclockwise();
void Stop();
void up1();
void up2();
void down1();
void down2();
void stop1();
void stop2();
void dump(decode_results *results);

void setup() {
  Serial.begin(9600);
  pinMode(ray_sensor_1, INPUT);
  pinMode(ray_sensor_2, INPUT);
  pinMode(ray_sensor,INPUT);
  pinMode(trace1, INPUT);
  pinMode(trace2, INPUT);
  pinMode(trace3, INPUT);
  pinMode(DHT11_PIN, INPUT);
  pinMode(fish_light1, OUTPUT);
  pinMode(fish_light2, OUTPUT); 
  pinMode(wheel1_pin1, OUTPUT);
  pinMode(wheel1_pin2, OUTPUT);
  pinMode(wheel1_pin3, OUTPUT);
  pinMode(wheel1_pin4, OUTPUT);
  pinMode(wheel2_pin1, OUTPUT);
  pinMode(wheel2_pin2, OUTPUT);
  pinMode(wheel2_pin3, OUTPUT);
  pinMode(wheel2_pin4, OUTPUT);
  pinMode(tonepin,OUTPUT);
  
  pinMode(tonepin,OUTPUT);
  length1=sizeof(tunejoy)/sizeof(tunejoy[0]); 
  length2=sizeof(tunesky)/sizeof(tunesky[0]);//计算长度
  length3=sizeof(tunetiger)/sizeof(tunetiger[0]);
  
  lcd.begin(16,2);
  lcd.clear();
  
  irrecv.enableIRIn(); 
}

void loop() {
  char code;
   while(Serial.available())
  {
    
   comm=Serial.read();
   if(code!=comm){
    lcd.clear();
   } 
   } 
   switch(comm){
     case '1':{stepperSpeed1=2;stepperSpeed2=2;Auto_Track();break;}
     case '2':{stepperSpeed1=2;stepperSpeed2=2;Ob_Avoid();break;}
     case '3':{IR_Control();break;}//有clear闪屏
     case '4':{stepperSpeed1=3;stepperSpeed2=3;Follow();break;}
     case '5':{lcd.setCursor(0, 0);lcd.print("Go");stepperSpeed1=1;stepperSpeed2=1;up1();up2();break;}
     case '6':{lcd.setCursor(0, 0);lcd.print("Back");stepperSpeed1=1;stepperSpeed2=1;down1();down2();break;}
     case '7':{lcd.setCursor(0, 0);lcd.print("Turn Right");stepperSpeed1=1;stepperSpeed2=1;up1();stop2();break;}
     case '8':{lcd.setCursor(0, 0);lcd.print("Turn Left");stepperSpeed1=1;stepperSpeed2=1;up2();stop1();break;}
     case '9':{lcd.setCursor(0, 0);lcd.print("Stop");Stop();break;}
     case 'a':{lcd.setCursor(0, 0);lcd.print("Clockwise");stepperSpeed1=1;stepperSpeed2=1;down1();up2();break;}
     case 'b':{lcd.setCursor(0, 0);lcd.print("Counterclockwise");stepperSpeed1=1;stepperSpeed2=1;down1();up2();break;}
     case 'c':{lcd.setCursor(0, 0);lcd.print("Playing music");Music_Play();break;}
     case 'd':{lcd.setCursor(0, 0);lcd.print("Environment");lcd.setCursor(0, 1);lcd.print("Monitor");Env_Monitor();break;}
     case 'e':{lcd.setCursor(0, 0);lcd.print("Light On");Light_On();break;}
     case 'f':{lcd.setCursor(0, 0);lcd.print("Light Off");Light_Off();break;}
     default: {lcd.setCursor(0, 0);lcd.print("Panda TMax");lcd.setCursor(0,1);lcd.print("Invalid Input");break;}
  }
}

void show_dist(){
  float volts = analogRead(ray_sensor)*0.0048828125;   
  dist= 65*pow(volts, -1.10);
  float play_dist=dist-20;
  
  if( dist<= 50){
    lcd.setCursor(0, 0) ;   
    lcd.print("distance ");
    lcd.print(play_dist);
    lcd.print("cm");
  }
  else{
    lcd.clear();
    lcd.setCursor(0, 0) ;   
    lcd.print("distance ");
    lcd.print("Far");
  }

}

void show_time(){
  unsigned long seconds;
  int s = 0, m = 0, h = 0, d = 0, mon = 0, y = 0;                       //time carry
  int second = 0, minute = 0, hour = 0, day = 0, month = 0, year = 0;   //current time
  int SECOND = 0, MINUTE = 0, HOUR = 0, DAY = 0, MONTH = 0, YEAR = 0;   //initial time
  
  seconds = millis()/1000;  
  second = (SECOND + seconds) % 60;                                   //calculate second
  m = (SECOND + seconds) / 60;                                        //carry of minute
  minute = (MINUTE + m) % 60;                                         //calculate minute
  h = (MINUTE + m) / 60;                                              //carry of hour
  hour = (HOUR + h) % 24;                                             //calculate hour


  
  lcd.setCursor(0, 1) ;   
  lcd.print(hour);
  lcd.print(":");
  lcd.print(minute);
  lcd.print(":");
  lcd.print(second);
}

void Auto_Track(){
  show_dist();//show distance in real time
  show_time();//show time in real time
  
  value_1 = digitalRead(ray_sensor_1);
  value_2 = digitalRead(ray_sensor_2);

  if (dist<30) {direc='7';lcd.setCursor(8, 1) ; lcd.print(" stop");}
  else if (dist<50&&value_1 == LOW && value_2 == LOW) {direc='1';lcd.setCursor(8, 1) ; lcd.print(" find");}
  else if (value_1 == HIGH && value_2 == LOW) {direc='4'; flag=1;lcd.setCursor(8, 1) ; lcd.print(" find");}
  else if (value_1 == LOW && value_2 == HIGH) {direc='3'; flag=0;lcd.setCursor(8, 1) ; lcd.print(" find");}
  else if (flag==0){direc='3';lcd.setCursor(8, 1) ; lcd.print(" miss");}
  else if (flag==1){direc='4';lcd.setCursor(8, 1) ; lcd.print(" miss");}
 
  switch(direc){
    case '1':{ Go(); break;}
    case '2':{ Back(); break;}
    case '3':{ Counterclockwise(); break;}
    case '4':{ Clockwise(); break;}
    case '5':{ Right(); break;}
    case '6':{ Left(); break;}
    case '7':{ Stop(); break;}
  }
}

void Ob_Avoid(){
  show_dist();
  show_time();
  
  value_1 = digitalRead(ray_sensor_1);
  value_2 = digitalRead(ray_sensor_2);
  
  if (dist<30) {direc='2';lcd.setCursor(8, 1) ; lcd.print(" warn");}
  else if (dist>50 && value_1 == HIGH && value_2 == HIGH) {direc='1';lcd.setCursor(8, 1) ; lcd.print(" safe");}
  else if (value_1 == HIGH && value_2 == HIGH) {direc='1';lcd.setCursor(8, 1) ; lcd.print(" adjust");}
  else if (value_1 == HIGH && value_2 == LOW) {direc='3'; flag=1;lcd.setCursor(8, 1) ; lcd.print(" adjust");}
  else if (value_1 == LOW && value_2 == HIGH) {direc='4'; flag=0;lcd.setCursor(8, 1) ; lcd.print(" adjust");}
  else if (flag==1){direc='3';lcd.setCursor(8, 1) ; lcd.print(" adjust");}
  else if (flag==0){direc='4';lcd.setCursor(8, 1) ; lcd.print(" adjust");}
 
  switch(direc){
    case '1':{ Go(); break;}
    case '2':{ Back(); break;}
    case '6':{ Left(); break;}
    case '5':{ Right(); break;}
    case '4':{ Clockwise(); break;}
    case '3':{ Counterclockwise(); break;}
    case '7':{ Stop(); break;}
  } 
}

void IR_Control(){
  lcd.setCursor(0, 0); 
  lcd.print("Remote Control ");
  show_time();
  
  if(irrecv.decode(&results)){
      dump(&results);  
  if(results.value == run_car){direction = 1;}
  else if(results.value == back_car){direction = 2;}
  else if(results.value == right_turn){direction = 3;}
  else if(results.value == left_turn){direction = 4;}
  else if(results.value == left_car){direction = 5;}
  else if(results.value == right_car){direction = 6;}
  else if(results.value == stop_car){direction =7 ;}
  
  irrecv.resume();// Receive the next value
  }
  
  switch(direction){
    case 0:break;
    case 1:{up1();up2();break;}
    case 2:{down1();down2();break;}
    case 3:{up1();down2();break;}
    case 4:{down1();up2();break;}
    case 5:{up1();stop2();break;}
    case 6:{up2();stop1();break;}
    case 7:{stop1();stop2();break;}
  }
}

void Follow(){
  lcd.setCursor(0, 0) ;   
  lcd.print("Panda TMax ");
  show_time();
  int c;
  int data[3];
  data[0] = digitalRead(trace1);//right
  data[1] = digitalRead(trace2);//middle
  data[2] = digitalRead(trace3);//left

  if(data[1]){c=1;}
  if(data[0]){c=2;}
  if(data[2]){c=3;}
  if(!data[0]&&!data[1]&&!data[2]){c=4;}
  switch(c){
    case 1:{Go();break;}
    case 2:{Right();break;}
    case 3:{Left();break;}
    case 4:{Stop();break;}
  }
}

void Music_Play()
{
  joy();
  delay(100);
  sky();
  delay(100);
  tiger();
  delay(100);
  }
  
void joy()
{
  /*for(int x=0;x<length1;x++)
  {
    tone(tonepin,tunejoy[x]);
    delay(500*durtjoy[x]);   //这里用来根据节拍调节延时，500这个指数可以自己调整，在该音乐中，我发现用500比较合适。
    noTone(tonepin);
  }*/
}

void sky()
{
   /*for(int x=0;x<length2;x++)
  {
    tone(tonepin,tunesky[x]);
    delay(500*durtsky[x]);   //这里用来根据节拍调节延时，500这个指数可以自己调整，在该音乐中，我发现用500比较合适。
    noTone(tonepin);
  }*/
}

void tiger()
{
  /* for(int x=0;x<length3;x++)
  {
    tone(tonepin,tunetiger[x]);
    delay(500*durttiger[x]);   //这里用来根据节拍调节延时，500这个指数可以自己调整，在该音乐中，我发现用500比较合适。
    noTone(tonepin);
  }*/
}

void Env_Monitor(){ 
  int chk = DHT.read11(DHT11_PIN); 
  switch (chk){ 
    case DHTLIB_OK: {  break;}
    case DHTLIB_ERROR_CHECKSUM: {  break;}                  
    case DHTLIB_ERROR_TIMEOUT: {  break; }
    case DHTLIB_ERROR_CONNECT: {  break;} 
    case DHTLIB_ERROR_ACK_L: {   break;}
    case DHTLIB_ERROR_ACK_H: {   break;} 
    default: { Serial.print("Unknown error,\t"); break;}
  }
  Serial.print("DHT.humidity");
  Serial.print(DHT.humidity,1);  
  Serial.print("DHT.temperature"); 
  Serial.print(DHT.temperature,1); 
  delay(2000);
}

void Go(){ //go straight
   for(int i=0;i<50;i++){
    up1();up2();
   }
}

void Back(){ //go back
  for(int i=0;i<50;i++){
    down1();down2();
  }
  
}

void Left(){//turn left
  for(int i=0;i<50;i++){
    up1();stop2();
  }
}

void Right(){//turn right
  for(int i=0;i<50;i++){
    stop1();up2();
  }
}

void Clockwise(){// clockwise rotate
  for(int i=0;i<50;i++){
    down1();up2();
  }
}

void Counterclockwise(){//counterwise rotate
  for(int i=0;i<50;i++){
    up1();down2();
  }
}

void Stop(){
  stop1();
  stop2();
}

 void down1()//left_back
{ 
  
  switch(_step1){
    case 0:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);//32A
    break;
    case 1:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);//10B
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 2:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 3:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 4:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 5:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
      case 6:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
    case 7:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
    default:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
    break;
  }
    _step1++;
 
  if(_step1>7){    _step1=0;  }
 
  delay(stepperSpeed1);
}

  void up2()
{ 
  
  switch(_step2){
    case 0:
    //stepperSpeed++;
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);//32A
    break;
    case 1:
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);//10B
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 2:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 3:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 4:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 5:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
      case 6:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
    case 7:
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
    default:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
    break;
  }
    _step2++;
 
  if(_step2>7){    _step2=0;  }
 
  delay(stepperSpeed2);

}

void up1()
  {  
    switch(_step1){
    case 0:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
    case 1:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
    case 2:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 3:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, HIGH);
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 4:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 5:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, HIGH);
      digitalWrite(wheel1_pin4, LOW);
    break;
      case 6:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
    break;
    case 7:
      digitalWrite(wheel1_pin1, HIGH);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, HIGH);
    break;
    default:
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
    break;
  }
   _step1++;
 
  if(_step1>7){    _step1=0;  }

  delay(stepperSpeed1);
} 

  void down2()
  {
   
    switch(_step2){
    case 0:
    //stepperSpeed++;
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
    case 1:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
    case 2:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 3:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, HIGH);
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 4:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 5:
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, HIGH);
      digitalWrite(wheel2_pin4, LOW);
    break;
      case 6:
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
    break;
    case 7:
      digitalWrite(wheel2_pin1, HIGH);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, HIGH);
    break;
    default:
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
    break;
  }
   _step2++;
 
  if(_step2>7){    _step2=0;  }

  delay(stepperSpeed2);
  } 

  void stop1()
{
      digitalWrite(wheel1_pin1, LOW);
      digitalWrite(wheel1_pin2, LOW);
      digitalWrite(wheel1_pin3, LOW);
      digitalWrite(wheel1_pin4, LOW);
       delay(stepperSpeed1);
}

void stop2()
{
      digitalWrite(wheel2_pin1, LOW);
      digitalWrite(wheel2_pin2, LOW);
      digitalWrite(wheel2_pin3, LOW);
      digitalWrite(wheel2_pin4, LOW);
       delay(stepperSpeed2);
}

void dump(decode_results *results)
{
  int count = results->rawlen;
  if (results->decode_type == UNKNOWN) 
  {
    Serial.println("Could not decode message");
    stop1();stop2();
  } 
}

void Light_On()
{
    digitalWrite(fish_light1,HIGH);
    digitalWrite(fish_light2,HIGH);
}

void Light_Off(){
    digitalWrite(fish_light1,LOW);
    digitalWrite(fish_light2,LOW);
}

