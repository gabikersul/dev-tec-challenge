# Technical Challenge Ilegra
   
It's a Java + Gradle application that reads files and analysis the data and output reports.

        
## Execution

- Start the application.
- You can add more files in %HOMEPATH/data/in following the model:
    - Salesperson id 001:
        - 001çCPFçNameçSalary
        - ex: 001ç1234567891234çPedroç50000
    
    - Client id 002:
        - 002çCNPJçNameçBusiness Area
        - ex: 002ç2345675434544345çJose da SilvaçRural
      
    - Sale id 003:
        - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
        - ex: 003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

- The .json files reports are generate and located at %HOMEPATH%/data/out.

- The report has: 
    
    - The quantity of clients.
    - The quantity of salespeople.
    - The best sale ID.
    - The worst salesperson.





