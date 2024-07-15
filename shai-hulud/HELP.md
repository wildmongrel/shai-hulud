# Release Notes

Version| Changes                      |Status|Progress
---|------------------------------|---|---
0.2.0| PoC 0.1.0 -> show on browser |WIP|Add rs from Proxy to Alice
0.1.0| PoC Conenct to Google.com    |Done
0.0.0| Initial                      |Done

---

## Backlogs
Tasks| Priority |Size
---|----------|---
Continuous use| 3        |3
Refactor| 2        |1

---

## Diagram

```plantuml
rectangle "Client Network" as clientNet{
  rectangle "Client Node" as client{
    rectangle "SH Client" as shClient
    database "File" as file  
  }
 
}

cloud Internet as internet{
}

rectangle "Server Network" as serverNet {
  rectangle "Server Node" as server{
      rectangle "RDP Server" as rdpServer
      rectangle "SH Server" as shServer
  }
}

client <--> shClient
client <--> internet
shClient <--> file

internet <--> server

rdpServer <--> shServer

'hide @unlinked 
```
