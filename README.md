# Серверная часть чат-приложения

Серверная часть многопользовательского чата с поддержкой авторизации и трансляцией сообщений между клиентами

## 🌟 Особенности
- Авторизация пользователей по никнейму
- Реализация сетевых пакетов (авторизация/сообщения)
- Трансляция сообщений всем подключённым клиентам
- Поддержка одновременных подключений
- Консольное управление сервером

## 📦 Требования
- JDK 11+
- Порт 8888 должен быть свободен
- Сетевое окружение с поддержкой TCP-соединений

## 🚀 Запуск сервера
1. Скомпилируйте проект:
```bash
javac -d out src/chat/*.java src/chat/packet/*.java
```
2. Запустите сервер:
```bash
java -cp out chat.ServerLoader
```
3. Для остановки введите в консоли:
```bash
/end
```

## 📡 Протокол взаимодействия
Структура пакетов:

| ID   | Тип       | Назначение                   |
|------|-----------|------------------------------|
| 1    | AUTHORIZE | Авторизация с указанием ника |
| 2    | MESSAGE   | Текст сообщения пользователя |

Последовательность работы:
1. Клиент отправляет пакет AUTHORIZE с никнеймом 
2. Сервер сохраняет сопоставление сокета и ника 
3. Клиент отправляет пакеты MESSAGE 
4. Сервер транслирует сообщения всем клиентам

## 🏗️ Структура проекта
```
src/chat/
├── ClientHandler.java    # Обработчик клиентского соединения
├── ServerHandler.java    # Прослушивание новых подключений
├── ServerLoader.java     # Основная логика сервера
│
└── packet/
    ├── Packet.java           # Базовый класс пакета
    ├── PacketAuthorize.java  # Пакет авторизации
    ├── PacketMessage.java    # Пакет сообщения
    └── PacketManager.java    # Фабрика пакетов
```

## 🛠️ Технические детали
- **Потоковая обработка:** Для каждого клиента создаётся отдельный поток 
- **Буферизация данных:** Использование DataInputStream/DataOutputStream 
- **Управление подключениями:** Хранение клиентов в HashMap<Socket, ClientHandler>
- **Система команд:** Консоль сервера поддерживает команду `/end`

## ⚠️ Ограничения
- [Клиентская часть](https://github.com/Omuny/Client_Chat) реализована в другом проекте
- Нет проверки уникальности никнеймов 
- Отсутствует обработка сетевых сбоев
