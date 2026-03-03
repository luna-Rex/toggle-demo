# 🌙 Luna Toggle Demo (Togglz)

這是一個展示 **Togglz** 功能開關 (Feature Toggle) 技術的專業 Demo 專案。透過本專案，您可以體驗如何在不重啟程式的前提下，動態切換前端介面與後端邏輯。

---

## 🚀 核心功能
- **動態開關**：即時切換歡迎訊息、促銷橫幅及運算邏輯。
- **使用者導向 (User Targeting)**：針對不同帳號（如 `demouser`）展示專屬功能（彩蛋）。
- **資料持久化**：開關狀態存放於 H2 資料庫（`demo_togglz` 表），重啟後設定不遺失。
- **SPA 體驗**：單頁式登入與操作，反應神速。
- **專業日誌**：完善的 SLF4J 英文日誌記錄。

## 🛠️ 技術棧
- **Framework**: Spring Boot 3.4.2
- **Language**: Java 21
- **Security**: Spring Security (Role-Based Access Control)
- **Database**: H2 (File-based persistence)
- **Library**: Togglz 4.4.0 (Starter & Console)

---

## 🏃 快速啟動
1. 確保您的環境已安裝 **OpenJDK 21+**。
2. 在專案根目錄執行：
   ```bash
   ./mvnw spring-boot:run
   ```
3. **資料庫初始化**：
   首次執行或需要重設資料庫時，請存取以下 API 端點進行初始化：
   👉 [http://localhost:8081/api/db/init](http://localhost:8081/api/db/init)
   *(此操作會檢查並建立 `DEMO_TOGGLZ` 資料表並載入初始開關設定)*

4. 打開瀏覽器存取：👉 [http://localhost:8081/index.html](http://localhost:8081/index.html)

## 👤 測試帳號
| 帳號 | 密碼 | 角色 (Role) | 權限內容 |
| :--- | :--- | :--- | :--- |
| **demouser** | password | ROLE_ADMIN | 可存取管理控制台 |
| **guest** | password | ROLE_USER | 僅限一般操作 |

---

## ⚙️ 管理員入口 (僅限 demouser)
- **Togglz 控制台**：[/togglz-console/index](/togglz-console/index)
- **H2 資料庫後台**：[/h2-console](/h2-console) (JDBC URL: `jdbc:h2:file:./data/toggledb`)

---

## 📝 開發備忘
本專案已通過 **大大** 的資安審查，所有敏感路徑均已去識別化處理。🌙✨
