# 미션 - 주문

## 🚀 진행 방식

1. 미션 진행은 [미션 진행 가이드 문서](https://github.com/develup-mission/docs/blob/main/mission-guide.md)를 따른다.

## 💻 기능 요구 사항

고객이 가게에 배달 주문을 하려고 합니다. 주문을 처리하기 위해 다음 사항을 고려해야 합니다.

- 최소 주문 금액이 설정되어 있습니다. 이 금액에 미치지 못하면 주문을 할 수 없습니다.
- 배달비는 주문 금액에 따라 달라집니다.
- 메인 메뉴를 주문할 때마다 서비스 만두를 1개씩 제공합니다.
- 오직 음료만으로는 주문할 수 없습니다.

### 주문 내역 입력

- 고객이 주문할 메뉴와 수량을 입력받습니다. 메뉴와 수량의 형식은 "${메뉴}(${수량}개)"입니다.
  - ex) 피자(2개), 감자튀김(1개), 콜라(3개)
- 주문할 메뉴는 콤마(,)로 구분합니다.
- 메뉴는 각각 최소 0개 ~ 최대 10개까지 주문할 수 있습니다.
- 주문할 수 있는 메뉴는 다음과 같습니다.
  - 메인: 피자(25,000원), 햄버거(10,000원), 치킨(23,000원)
  - 사이드: 감자튀김(5,000원), 치즈스틱(7,000원), 샐러드(8,000원)
  - 음료: 콜라(2,000원), 제로 콜라(2,500원), 오렌지 주스(3,000원)
- 주문 형식이나 주문 조건이 잘못된 경우 "[ERROR]: "로 시작하는 예외 메시지를 출력하고 종료합니다.
  - ex) "[ERROR]: 음료만으로는 주문할 수 없습니다."

### 최소 주문 금액

- 최소 주문 금액은 배달비를 제외하고 30,000원입니다.
- 최소 주문 금액에 미치지 못할 경우 "[ERROR]: 최소 주문 금액을 만족하지 못했습니다."를 출력하고 종료합니다.

### 배달비

- 주문 금액이 50,000원 미만일 경우 배달비는 2,000원입니다.
- 주문 금액이 50,000원 이상 ~ 100,000원 미만일 경우 배달비는 1,000원입니다.
- 주문 금액이 100,000원 이상일 경우 배달비는 없습니다.

### 서비스

- 메인 메뉴를 주문할 때마다 서비스 만두를 1개씩 제공합니다.
- 서비스 만두는 서비스 내역에 추가됩니다.
  - ex) 서비스 만두(1개)
- 서비스 만두가 없을 경우 서비스 내역을 출력하지 않습니다.

### 입출력 요구 사항

#### 입력

- 주문할 메뉴와 수량을 입력받습니다.

#### 출력

- 주문 내역을 출력합니다.
- 총 주문 금액을 출력합니다.
- 배달비를 출력합니다.
- 서비스를 출력합니다.
- 최종 결제 금액을 출력합니다.

#### 예외 발생 예시

- 주문할 수 있는 메뉴가 아닌 경우

```
주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)
피망(2개), 콜라(3개)
[ERROR]: 주문 형식이 잘못되었습니다.
```

- 최소 주문 금액을 만족하지 못한 경우

```
주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)
피자(2개)
[ERROR]: 최소 주문 금액을 만족하지 못했습니다.
```

#### 실행 결과 예시

```
주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)
햄버거(5개), 샐러드(2개), 콜라(3개)

[주문 내역]
햄버거(5개): 50,000원
샐러드(2개): 16,000원
콜라(3개): 6,000원
총 주문 금액: 72,000원
배달비: 1,000원

[서비스]
서비스 만두(5개)

[최종 결제 금액]
73,000원
```

```
주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)
감자튀김(9개), 샐러드(8개), 콜라(8개), 오렌지 주스(9개)

[주문 내역]
감자튀김(9개): 45,000원
샐러드(8개): 64,000원
콜라(8개): 16,000원
오렌지 주스(9개): 27,000원
총 주문 금액: 152,000원
배달비: 0원

[최종 결제 금액]
152,000원
```
## 기능 목록

- [x] 배달비 계산
  - [x] 주문 금액이 50,000원 미만일 경우 배달비는 2,000원입니다.
  - [x] 주문 금액이 50,000원 이상 ~ 100,000원 미만일 경우 배달비는 1,000원입니다.
  - [x] 주문 금액이 100,000원 이상일 경우 배달비는 없습니다.
- [x] 최소 주문 금액 검사
  - [x] 최소 주문 금액은 배달비를 제외하고 30,000원입니다.
  - [x] 최소 주문 금액에 미치지 못할 경우 `"[ERROR]: 최소 주문 금액을 만족하지 못했습니다."`를 출력하고 종료합니다.
- [x] 주문 생성
  - [x] 음료만으로 주문한 경우 주문 불가
  - [x] 메인 메뉴를 주문할 때마다 서비스 만두를 1개씩 제공합니다. **낱개 하나 당 서비스 만두 하나로 설정**
- 주문 메뉴 입력
  - [x] 주문 형식 검증
    - [x] 주문할 메뉴는 콤마로 구분
    - [x] 메뉴는 각각 최소 0개, 최대 10개 주문 가능
    - [x] 메뉴와 수량의 형식은 `"${메뉴}(${수량}개)"`
- [x] 메뉴 정보 관리
  - [x] enum
- 서비스 내역
  - [x] 서비스 만두는 서비스 내역에 추가됩니다.
    - ex) 서비스 만두(1개)
  - [x] 서비스 만두가 없을 경우 서비스 내역을 출력하지 않습니다.