









USE master
DROP DATABASE IF EXISTS SWPProject2
GO


CREATE DATABASE SWPProject2
GO
USE SWPProject2
GO

---------------------------------------------------------------------------- Create table for all attribute --------------------------------------------------------------------------------------------------------- 
   Create table Roles (
            RoleId      INT		            IDENTITY(1, 1)		 PRIMARY KEY,
			RoleName    NVARCHAR(20)							     NOT NULL
			)
 go    

CREATE TABLE Employee (
	EmployeeID				 INT		            IDENTITY(1, 1)		 PRIMARY KEY,
	EmployeeName		     NVARCHAR(50)							     NOT NULL,
	EmployeeAvatar           NVARCHAR(2000),
	Username                 Nvarchar(20)                                NOT NULL,
	Birthday                 Date,
	EmployeeSex              int ,
	EmployeePassword         NVARCHAR(50)                                NOT NULL,
	EmployeeAddress          NVARCHAR(50) ,
	EmployeePhone            NVARCHAR(50) ,
	EmployeeStatus           Bit                                         ,
	RoleId		    INT						
	FOREIGN KEY REFERENCES Roles(RoleId)               
	
)
go



 --Create table WordSheet
CREATE TABLE Shifts (
    ShiftID                INT       IDENTITY(1, 1)	     PRIMARY KEY,
	ShiftName				 NVARCHAR(100)							    NOT NULL, 
	EmployeeRole		   int							    NOT NULL, 
	StartTime		       Time		                        NOT NULL,  
	EndTime                Time							    NOT NULL,
	StartDate			   Date								NOT NULL, 
	EndDate				   Date							NOT NULL,
	CoefSalary             Float                      NOT NULL ,
	EmployeeIDFirst				INT		
	FOREIGN KEY (EmployeeIDFirst) REFERENCES Employee(EmployeeID),
	EmployeeIDSecond				INT								
	FOREIGN KEY (EmployeeIDSecond) REFERENCES Employee(EmployeeID),
)


--Create table Work
CREATE TABLE Payroll (
	EmployeeID INT FOREIGN KEY REFERENCES Employee(EmployeeID),
	Salary FLOAT,
	FinedSalary FLOAT DEFAULT 0,
)

CREATE TABLE Applications (
	ApplicationID		    INT		            IDENTITY(1, 1)		 PRIMARY KEY,
	ShiftID				INT							     FOREIGN KEY REFERENCES Shifts(ShiftID),
	LeaveReason				NVARCHAR(50),
	ApplicationStatus       BIT                                DEFAULT 0,
	EmployeeID		    INT						
	FOREIGN KEY REFERENCES Employee(EmployeeID),
	IsApproved BIT DEFAULT 0
)


--Create table Attendance
CREATE TABLE Attendance (
    AttendanceID              INT                 IDENTITY(1, 1)	     PRIMARY KEY,
	CheckInDate                   DATE, 
	CheckInTime				  TIME,
	CheckOutDate				DATE,
	CheckOutTime				TIME,
	LateTime                  INT,
	SoonTime				INT,
	EmployeeID			      INT
	FOREIGN KEY REFERENCES Employee(EmployeeID),
	ShiftID			    INT
	FOREIGN KEY REFERENCES Shifts(ShiftID),
	ShiftComplete FLOAT DEFAULT 0,
	FinedShift FLOAT DEFAULT 0,
)



--Create table categories
CREATE TABLE Categorie (
	CategoryID				INT						IDENTITY(1, 1)   		PRIMARY KEY,
	CategoryName			NVARCHAR(50)			UNIQUE					NOT NULL

)
GO


--Create table Products
CREATE TABLE Product (
	ProductID			INT 	        IDENTITY(1, 1)     	     PRIMARY KEY,
	ProductLink         NVARCHAR(2000)                                         ,
	ProductName		    VARCHAR(2000)				             NOT NULL,
	ProductPrice		MONEY			                         NOT NULL,
	Disscount       	MONEY			DEFAULT '0.00'			 NOT NULL,   
	ProductStatus       Bit                                      NOT NULL,
	Quatity             INt,
	CategoryID		    INT						
	FOREIGN KEY REFERENCES Categorie(CategoryID),
	EmployeeID		    INT						
	FOREIGN KEY REFERENCES Employee(EmployeeID)
)
GO



--create table customers
CREATE TABLE Customer (
	CustomerID			    INT               IDENTITY(1, 1) 				PRIMARY KEY,
	CustomerName		    NVARCHAR(50)									NOT NULL,
	Phone                   VARCHAR(20)                                     NOT NULL,

)
GO
--create table customers
CREATE TABLE Feedback (
	FeedbackID			    INT               IDENTITY(1, 1) 				PRIMARY KEY,
	Comment				  NVARCHAR(50)									NOT NULL,
	Rate                  INT                                     NOT NULL,
	EmployeeID		    INT						
	FOREIGN KEY REFERENCES Employee(EmployeeID),
	CustomerID				INT
	FOREIGN KEY REFERENCES Customer(CustomerID),


)
GO





GO
--create table voucher
CREATE TABLE Voucher (
	VoucherID				    INT					IDENTITY(1, 1) 			 PRIMARY KEY,
	VoucherName                 NVARCHAR(200)								 NOT NULL,
	DateStart					DATETIME									 NOT NULL,
	DateEnd			     		DATETIME									 NOT NULL,
	VoucherStatus				BIT,										
	Condition					INT,
	Value						INT,
	Quatity						INT											 NOT NULL,
	EmployeeID		    INT						
	FOREIGN KEY REFERENCES Employee(EmployeeID)
	
)
GO


--create table Orders
CREATE TABLE Orders (
	OrderID				    INT					IDENTITY(1, 1) 			PRIMARY KEY,
	OrderDate				DATETIME										NOT NULL,
	CustomerID				INT
	FOREIGN KEY REFERENCES Customer(CustomerID),
	VoucherID			     INT       
	FOREIGN KEY REFERENCES Voucher(VoucherID)  ,
	EmployeeID				INT
	FOREIGN KEY REFERENCES Employee(EmployeeID)
)

--create table Order
CREATE TABLE Orderdetail (
    OrderdetailID                 INT 			IDENTITY(1, 1) 		PRIMARY KEY,
	ProdcutID		        INT               
	FOREIGN KEY REFERENCES Product(ProductID)       ,
	Quantity                INT                                     NOT NULL,	           
	OrderID			        INT       
	FOREIGN KEY REFERENCES Orders(OrderID)  
	)
GO









-- ------------------------------------------------------- Insert data for all table------------------------------------------------------------------------------------------------------------------------------------------------
--Add data for Account ok
--Manager : 1  
--Sales :2
--Guard :3


--Add data for Role
INSERT INTO Roles(RoleName) VALUES('Manager')
INSERT INTO Roles(RoleName) VALUES('Sales')
INSERT INTO Roles(RoleName) VALUES('Guard')
go

INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Nguyen Van Tuan','Tuan','123',1,'1997-05-23',1,'1234567890',N'Ho Chi Minh','True',N'6.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Dang Thu Ha','Ha','123',2,'2002-06-26',2,'0326987512',N'Binh Dinh','True',N'1.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Ho Van Thanh','Thanh','123',1,'2001-07-13',2,'0265014325',N'Dong Nai','True',N'4.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Huyng Thao Trang','Trang','123',2,'2003-02-03',2,'03265984210',N'Ninh Binh','True',N'3.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Le Tan Luan','Luan','123',1,'2002-10-17',3,'03265986321',N'Thah Hoa','True',N'4.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Pham Huu Phuoc','Phuoc','123',2,'2000-05-30',3,'02659864175',N'Binh Thuan','True',N'5.jpg')

INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Pham Tran Huy','Huy','123',1,'1997-05-23',2,'1234567890',N'Ho Chi Minh','True',N'5.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Tran Hieu','Hieu','123',2,'2002-06-26',3,'0326987512',N'Binh Dinh','True',N'6.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Pham Kiet','Kiet','123',1,'2001-07-13',2,'0265014325',N'Dong Nai','True',N'2.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Ho Thao','Thao','123',2,'2003-02-03',2,'03265984210',N'Ninh Binh','True',N'3.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Le Tan Bang','Bang','123',1,'2002-10-17',3,'03265986321',N'Thanh Hoa','True',N'4.jpg')
INSERT INTO Employee(EmployeeName,Username,EmployeePassword,EmployeeSex,Birthday,RoleId,EmployeePhone,EmployeeAddress,EmployeeStatus,EmployeeAvatar)VALUES(N'Nhat Hao','Hao','123',1,'2000-05-30',3,'02659864175',N'Binh Thuan','True',N'5.jpg')

go

--Add data for Shift no  ok
---Shiftdate
-- Ngày thường :1
-- Chủ Nhật:2
-- Lễ: 3
--roleEmployee
--nhân viên :1
--bảo vệ : 2

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-19','2023-06-19', 1.5,     2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-19','2023-06-19', 1.5 ,      7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-19','2023-06-20', 2 ,       2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-19','2023-06-19',1.5,     5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-19','2023-06-20', 2 ,     6 ,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-20','2023-06-20', 1.5,     2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-20','2023-06-20', 1.5 ,     7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-20','2023-06-21', 2 ,         2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-20','2023-06-20',1.5,      5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-20','2023-06-21', 2 ,     6 ,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-21','2023-06-21', 1.5,    2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-21','2023-06-21', 1.5 ,      7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-21','2023-06-22', 2 , 2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-21','2023-06-21',1.5,     5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-21','2023-06-22', 2 ,      6 ,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-22','2023-06-22', 1.5,      2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-22','2023-06-22', 1.5 ,      7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-22','2023-06-23', 2 ,     2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-22','2023-06-22',1.5,      5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-22','2023-06-23', 2 ,      6,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-23','2023-06-23', 1.5,     2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-23','2023-06-23', 1.5 ,      7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-23','2023-06-24', 2 ,  2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-23','2023-06-23',1.5,     5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-23','2023-06-24', 2 ,     6 ,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-24','2023-06-24', 1.5,     2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-24','2023-06-24', 1.5 ,     7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-24','2023-06-25', 2 ,  2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-24','2023-06-24',1.5,      5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-24','2023-06-25', 2 ,      6 ,  12 )

INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',2,'06:00:00','12:00:00','2023-06-25','2023-06-25', 3,     2,     4 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',2,'12:00:00','18:00:00','2023-06-25','2023-06-25', 3 ,      7,   9 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 3',2,'18:00:00','06:00:00','2023-06-25','2023-06-26', 4 ,  2,     4  )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 1',3,'06:00:00','18:00:00','2023-06-25','2023-06-25',3,      5,   8 )
INSERT INTO Shifts(ShiftName,EmployeeRole,StartTime,EndTime,StartDate,EndDate,CoefSalary,EmployeeIDFirst,EmployeeIDSecond) VALUES('Slot 2',3,'18:00:00','06:00:00','2023-06-25','2023-06-26', 4 ,      6 ,  12 )



-- Ket thuc

										  
--  Add data for categories ok
INSERT INTO Categorie(CategoryName) VALUES('Softdrink')
INSERT INTO Categorie(CategoryName) VALUES('Snack')
INSERT INTO Categorie(CategoryName) VALUES('Mask')
INSERT INTO Categorie(CategoryName) VALUES('Cake')
INSERT INTO Categorie(CategoryName) VALUES('Fast Food')



--Add data for Product

INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(1,'Guava TEA',25000,20,'true',3.0, N'HỒNG TRÀ SỮA NƯỚNG.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(1,'MILK FLAVOR',20000,30,'true',0.0, N'SỮA VỊ DƯA LƯỚI.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(1,'ROOKED MILK TEA',27000,50,'true',5.0, N'hongtra.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(1,'FRESH MILK COFFEE',25000,10,'true',3.0, N'CÀ PHÊ SỮA TƯƠI.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(1,'OLONG TEA',20000,25,'true',0.0,N'TRÀ OLONG VỊ ĐÀO.jpg')

INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(2,'ORION Potato Snack',12000,12,'true',1.0,N'orion-snack-khoai-tây-bốn-vị-150g.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(2,'SWING MAXX KTC ',15000,8,'true',3.0,N'swing-ktc-vị-bò-nướng-ny-63g.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(2,'OSTAR Fries',15000,7,'true',0.0,N'ostar-khoai-tây-chiên-vị-trứng-muối-56g.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(3,'Mask 3D',30000,20,'true',0.0, N'mayan-khẩu-trang-3d-medi-5-cai.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(4,'BROWNIE PARFAIT',32000,40,'true',0.0,N'BROWNIE PARFAIT.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(4,'CHOCOLATE CAKE',30000,37,'true',3.0, N'BROWNIE PARFAIT.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(4,'CREPES',35000,28,'true',5.0, N'63e04d8157339_2a4ae6fdc600139d449fe1522e24df8b.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(4,'PANNA COTTA MEMBERS ',40000,9,'true',5.0,N'BROWNIE PARFAIT.jpg')

INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'Spicy Pork SALAD ',38000,5,'true',5.0, N'SALAD THỊT HEO CAY SỐT MẮM TỎI.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'TEMAKI ONIGIRI SHRIMP MAYO',30000,16,'true',3.0, N'CREPE.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'Kimchi Grilled Beef Rice',35000,27,'true',0.0, N'CƠM BÒ NƯỚNG KIM CHI.png')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'Japanese Curry Rice',40000,20,'true',4.0, N'CƠM CÀ RI NHẬT & GÀ CHIÊN XÙ.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'Fried rice with sausage & kimchi',25000,10,'true',3.0, N'CƠM CÀ RI NHẬT & GÀ CHIÊN XÙ.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'TOMYUM SEAFOOD RICE',36000,9,'true',5.0, N'CƠM HẢI SẢN XỐT TOMYUM..jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'FEMALE RICE ',35000,30,'true',5.0,N'CƠM SƯỜN CHẢ.png')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'HOTDOG',20000,40,'true',3.0, N'HOTDOG XÚC XÍCH.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'Noodles',20000,21,'true',0.0,N'MÌ KHÔ XÁ XÍU.jpg')
INSERT INTO Product(CategoryID,ProductName,ProductPrice,Quatity,ProductStatus,Disscount,ProductLink) VALUES(5,'TUNA SALAD',36000,20,'true',2.0, N'00400.jpg')





--Add data for customer ok
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Huy','0994874853')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Nhat','08937587693')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Ngan','09838489685')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Son','0994874853')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Van','093365897443')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Nam','0994874853')
INSERT INTO Customer(CustomerName,Phone) VALUES(N'Dung','0392272356')

--ok
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES(N'Không sử dụng voucher', '2023-01-01', '2030-01-01', 'true', 0, 1000000000, 0, 1)
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES(N'Voucher tháng 7 - Giảm 10.000VND cho hóa đơn trên 100.000VND', '2023-07-01', '2023-07-31', 'true', 100000, 100, 10000, 1)
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES(N'Voucher tháng 8 - Giảm 20.000VND cho hóa đơn trên 200.000VND', '2023-08-01', '2023-08-31','false', 200000, 100, 20000, 1)
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES (N'Voucher tháng 9 - Giảm 30.000VND cho hóa đơn trên 300.000VND', '2023-09-01', '2023-09-30','false', 300000, 100, 30000, 1)
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES (N'Voucher tháng 10 - Giảm 40.000VND cho hóa đơn trên 400.000VND', '2023-10-01', '2023-10-31','false', 400000, 100, 40000, 1)
INSERT INTO Voucher(VoucherName,DateStart,DateEnd,VoucherStatus,Condition,Quatity,Value,EmployeeID) VALUES (N'Voucher tháng 11 - Giảm 50.000VND cho hóa đơn trên 500.000VND', '2023-11-01', '2023-11-30','false', 500000, 100, 50000, 1)




-- Data for Orders ok
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(1, '2023-07-07', 2, 2)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(2, '2023-07-07', 2, 3)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(3, '2023-07-07', 2, 2)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(4, '2023-07-07', 2, 3)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(5, '2023-07-07', 2, 2)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(6, '2023-07-07', 2, 3)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(7, '2023-07-07', 2, 2)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(2, '2023-07-08', 2, 4)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(1, '2023-07-08', 2, 4)
INSERT INTO Orders(CustomerID,OrderDate,VoucherID, EmployeeID ) VALUES(3, '2023-07-08', 2, 4)



--data for order_details
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(1,2,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(1,5,6)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(1,10,3)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(2,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(2,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(2,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(3,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(3,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(3,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(4,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(4,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(4,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(5,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(5,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(5,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(6,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(6,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(6,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(7,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(7,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(7,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(8,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(8,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(8,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(9,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(9,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(9,7,1)

INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(10,6,3)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(10,5,1)
INSERT INTO Orderdetail(OrderID,ProdcutID,Quantity) VALUES(10,7,1)
					

-- add data for feedbak ok
INSERT INTO Feedback(CustomerID,Comment,Rate) VALUES(1, 'Great service!', 5)
INSERT INTO Feedback(CustomerID,Comment,Rate) VALUES(2, 'Good experience overall.', 4)
INSERT INTO Feedback(CustomerID,Comment,Rate) VALUES(3, 'Could be better.', 3)
INSERT INTO Feedback(CustomerID,Comment,Rate) VALUES(6, 'Needs improvement in packaging.', 3)
INSERT INTO Feedback(CustomerID,Comment,Rate) VALUES(5, 'Excellent customer support.', 5)

-- add Attendance
--add payroll
--INSERT INTO Payroll(EmployeeID,ShiftID,Hourswork) VALUES(1,1,'50')


INSERT INTO Payroll (EmployeeID, Salary)
SELECT EmployeeID, 0
FROM Employee;

INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(1, 'Travel', 2)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(21, 'Headache', 2)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(20, 'None', 6)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(10, 'Vacation', 6)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(17, 'See the doctor', 9)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(22, 'For a interview', 7)

INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(12, 'Stomach-ache', 9)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(8, 'Go for a pregnancy test', 4)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(14, 'For a interview', 5)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(18, 'COVID-19', 4)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(29, 'None', 5)
INSERT INTO Applications(ShiftID, LeaveReason, EmployeeID) VALUES(35, 'For a interview', 6)

--INSERT INTO Attendance

INSERT INTO Attendance(CheckInDate, CheckInTime, CheckOutDate, CheckOutTime, EmployeeID, ShiftID)
VALUES ('2023-6-24', '6:9:0', '2023-6-24', '12:4:00', 2, 26)

INSERT INTO Attendance(CheckInDate, CheckInTime, CheckOutDate, CheckOutTime, EmployeeID, ShiftID)
VALUES ('2023-6-23', '18:15:0', '2023-6-24', '6:12:00', 2, 23)

INSERT INTO Attendance(CheckInDate, CheckInTime, CheckOutDate, CheckOutTime, EmployeeID, ShiftID)
VALUES ('2023-6-23', '5:54:0', '2023-6-23', '18:12:00', 5, 24)

INSERT INTO Attendance(CheckInDate, CheckInTime, CheckOutDate, CheckOutTime, EmployeeID, ShiftID)
VALUES ('2023-6-24', '7:3:0', '2023-6-24', '12:10:00', 4, 26)