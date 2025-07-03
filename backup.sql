-- MySQL dump 10.13  Distrib 9.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: testing
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `ma_so` varchar(255) NOT NULL,
  `ma_so_thue` varchar(255) DEFAULT NULL,
  `so_tk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_so`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuong_trinh_dao_tao`
--

DROP TABLE IF EXISTS `chuong_trinh_dao_tao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuong_trinh_dao_tao` (
  `khoa_hoc` varchar(255) NOT NULL,
  `ma_nganh` bigint DEFAULT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`khoa_hoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuong_trinh_dao_tao`
--

LOCK TABLES `chuong_trinh_dao_tao` WRITE;
/*!40000 ALTER TABLE `chuong_trinh_dao_tao` DISABLE KEYS */;
INSERT INTO `chuong_trinh_dao_tao` VALUES ('K47',1,'Không có'),('K50',6,'Không có');
/*!40000 ALTER TABLE `chuong_trinh_dao_tao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctdt_hp`
--

DROP TABLE IF EXISTS `ctdt_hp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctdt_hp` (
  `chuong_trinh_dao_tao_id` varchar(255) NOT NULL,
  `hoc_phan_id` varchar(255) NOT NULL,
  KEY `FKc8aatkkhr88qs9i1qjiyxn3i2` (`hoc_phan_id`),
  KEY `FKkovxqtj6uvla55pci4g827l4c` (`chuong_trinh_dao_tao_id`),
  CONSTRAINT `FKc8aatkkhr88qs9i1qjiyxn3i2` FOREIGN KEY (`hoc_phan_id`) REFERENCES `hoc_phan` (`ma_hp`),
  CONSTRAINT `FKkovxqtj6uvla55pci4g827l4c` FOREIGN KEY (`chuong_trinh_dao_tao_id`) REFERENCES `chuong_trinh_dao_tao` (`khoa_hoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctdt_hp`
--

LOCK TABLES `ctdt_hp` WRITE;
/*!40000 ALTER TABLE `ctdt_hp` DISABLE KEYS */;
INSERT INTO `ctdt_hp` VALUES ('K47','CT100'),('K47','CT101'),('K47','CT112'),('K47','CT121'),('K47','CT124'),('K47','CT126'),('K47','CT127'),('K47','CT172'),('K47','CT173'),('K47','CT174'),('K47','CT175'),('K47','CT176'),('K47','CT177'),('K47','CT178'),('K47','CT179'),('K47','CT180'),('K47','CT182'),('K47','CT188'),('K47','CT190'),('K47','CT200'),('K47','CT202'),('K47','CT205'),('K47','CT206'),('K47','CT207'),('K47','CT211'),('K47','CT212'),('K47','CT221'),('K47','CT222'),('K47','CT223'),('K47','CT224'),('K47','CT225'),('K47','CT226'),('K47','CT227'),('K47','CT228'),('K47','CT229'),('K47','CT230'),('K47','CT231'),('K47','CT232'),('K47','CT233'),('K47','CT234'),('K47','CT235'),('K47','CT237'),('K47','CT238'),('K47','CT251'),('K47','CT272'),('K47','CT273'),('K47','CT274'),('K47','CT296'),('K47','CT332'),('K47','CT335'),('K47','CT338'),('K47','CT344'),('K47','CT428'),('K47','CT439'),('K47','CT476'),('K47','CT482'),('K47','CT507'),('K47','CT555'),('K47','FL001'),('K47','FL002'),('K47','FL003'),('K47','KL001'),('K47','KN001'),('K47','KN002'),('K47','ML007'),('K47','ML014'),('K47','ML016'),('K47','ML018'),('K47','ML019'),('K47','ML021'),('K47','QP011'),('K47','QP012'),('K47','QP013'),('K47','TC100'),('K47','TN001'),('K47','TN002'),('K47','TN010'),('K47','TN012'),('K47','XH011'),('K47','XH012'),('K47','XH014'),('K47','XH023'),('K47','XH024'),('K47','XH025'),('K47','XH028'),('K50','CT100'),('K50','CT101'),('K50','CT112'),('K50','CT121'),('K50','CT124'),('K50','CT126'),('K50','CT127'),('K50','CT172'),('K50','CT173'),('K50','CT174'),('K50','CT175'),('K50','CT176'),('K50','CT177'),('K50','CT178'),('K50','CT179'),('K50','CT180'),('K50','CT182'),('K50','CT188'),('K50','CT190'),('K50','CT200'),('K50','CT202'),('K50','CT205'),('K50','CT206'),('K50','CT207'),('K50','CT211'),('K50','CT212'),('K50','CT221'),('K50','CT222'),('K50','CT223'),('K50','CT224'),('K50','CT225'),('K50','CT226'),('K50','CT227'),('K50','CT228'),('K50','CT229'),('K50','CT230'),('K50','CT231'),('K50','CT232'),('K50','CT233'),('K50','CT234'),('K50','CT235'),('K50','CT237'),('K50','CT238'),('K50','CT251'),('K50','CT272'),('K50','CT273'),('K50','CT274'),('K50','CT296'),('K50','CT332'),('K50','CT335'),('K50','CT338'),('K50','CT344'),('K50','CT428'),('K50','CT439'),('K50','CT476'),('K50','CT482'),('K50','CT507'),('K50','CT555'),('K50','FL001'),('K50','FL002'),('K50','FL003'),('K50','KL001'),('K50','KN001'),('K50','KN002'),('K50','ML007'),('K50','ML014'),('K50','ML016'),('K50','ML018'),('K50','ML019'),('K50','ML021'),('K50','QP011'),('K50','QP012'),('K50','QP013'),('K50','TC100'),('K50','TN001'),('K50','TN002'),('K50','TN010'),('K50','TN012'),('K50','XH011'),('K50','XH012'),('K50','XH014'),('K50','XH023'),('K50','XH024'),('K50','XH025'),('K50','XH028');
/*!40000 ALTER TABLE `ctdt_hp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diemtb` double DEFAULT NULL,
  `ma_hoc_ky` bigint DEFAULT NULL,
  `sinh_vien_ma_so` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giang_vien`
--

DROP TABLE IF EXISTS `giang_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giang_vien` (
  `ma_so` varchar(255) NOT NULL,
  `ma_so_thue` bigint DEFAULT NULL,
  `so_tk` bigint DEFAULT NULL,
  `ma_khoa` bigint DEFAULT NULL,
  PRIMARY KEY (`ma_so`),
  KEY `FKqswt0bqfpejekbusavs1iy9qy` (`ma_khoa`),
  CONSTRAINT `FKqswt0bqfpejekbusavs1iy9qy` FOREIGN KEY (`ma_khoa`) REFERENCES `khoa` (`ma_khoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giang_vien`
--

LOCK TABLES `giang_vien` WRITE;
/*!40000 ALTER TABLE `giang_vien` DISABLE KEYS */;
/*!40000 ALTER TABLE `giang_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_ky`
--

DROP TABLE IF EXISTS `hoc_ky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_ky` (
  `ma_hoc_ky` bigint NOT NULL AUTO_INCREMENT,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  `ten_hoc_ky` varchar(255) DEFAULT NULL,
  `nam_hoc_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ma_hoc_ky`),
  KEY `FK1saej82m85oly0ccdor0b1txh` (`nam_hoc_id`),
  CONSTRAINT `FK1saej82m85oly0ccdor0b1txh` FOREIGN KEY (`nam_hoc_id`) REFERENCES `nam_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_ky`
--

LOCK TABLES `hoc_ky` WRITE;
/*!40000 ALTER TABLE `hoc_ky` DISABLE KEYS */;
INSERT INTO `hoc_ky` VALUES (1,'2021-09-01','2022-01-18','Học Kỳ 1',1),(2,'2022-01-13','2022-05-31','Học Kỳ 2',1),(3,'2022-06-01','2022-08-31','Học Kỳ 3',1),(4,'2023-09-01','2023-01-18','Học Kỳ 1',2),(5,'2023-01-13','2023-05-31','Học Kỳ 2',2),(6,'2023-05-01','2023-08-31','Học Kỳ 3',2);
/*!40000 ALTER TABLE `hoc_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_phan`
--

DROP TABLE IF EXISTS `hoc_phan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_phan` (
  `ma_hp` varchar(255) NOT NULL,
  `hoc_phan_tien_quyet` varchar(255) DEFAULT NULL,
  `loai_hp` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `ten_hp` varchar(255) DEFAULT NULL,
  `tin_chi` int NOT NULL,
  PRIMARY KEY (`ma_hp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_phan`
--

LOCK TABLES `hoc_phan` WRITE;
/*!40000 ALTER TABLE `hoc_phan` DISABLE KEYS */;
INSERT INTO `hoc_phan` VALUES ('CT083','','Đại cương','','Ngôn ngữ truyền thông',2),('CT084','','Đại cương','','Cơ sở kỹ thuật đồ họa',2),('CT085','','Cơ sở ngành','','Nhập môn Truyền thông đa phương tiện',3),('CT086','','Cơ sở ngành','','Nhập môn Web Đa phương tiện',3),('CT087','','Cơ sở ngành','','Nền tảng Kỹ xảo số',3),('CT088','','Cơ sở ngành','','Nhập môn Tiếp thị kỹ thuật số',3),('CT089','CT086','Cơ sở ngành','','Nhập môn Lập trình đa phương tiện',4),('CT092','','Cơ sở ngành','','Kỹ thuật dựng phim ảnh kỹ thuật số',3),('CT093','','Cơ sở ngành','','Kỹ xảo số',3),('CT094','','Cơ sở ngành','','Quy trình sáng tạo sản phẩm đa phương tiện',3),('CT095','','Cơ sở ngành','','Dự án phim hoạt hình 3D',3),('CT096','','Cơ sở ngành','','Tiếp thị nội dung',3),('CT097','','Cơ sở ngành','','Phát triển ứng dụng Web',3),('CT098','','Chuyên ngành','','Phát triển ứng dụng IoT',3),('CT099','CT296','Chuyên ngành','','Blockchain và ứng dụng',3),('CT100','','Đại cương','','Kỹ năng học đại học',2),('CT101','','Cơ sở ngành','','Lập trình căn bản A',4),('CT112','CT178','Cơ sở ngành','','Mạng máy tính',3),('CT113','','Cơ sở ngành','','Nhập môn công nghệ phần mềm',2),('CT121','CT101','Chuyên ngành','','Tin học lý thuyết',3),('CT124','','Cơ sở ngành','','Phương pháp tính – CNTT',2),('CT126','','Chuyên ngành','','Lý thuyết xếp hàng',2),('CT127','','Chuyên ngành','','Lý thuyết thông tin',2),('CT172','','Cơ sở ngành','','Toán rời rạc',4),('CT173','','Cơ sở ngành','','Kiến trúc máy tính',3),('CT174','CT177','Cơ sở ngành','','Phân tích và thiết kế thuật toán',3),('CT175','CT177','Cơ sở ngành','','Lý thuyết đồ thị',4),('CT176','CT101','Cơ sở ngành','','Lập trình hướng đối tượng',3),('CT177','','Cơ sở ngành','','Cấu trúc dữ liệu',3),('CT178','CT173','Cơ sở ngành','','Nguyên lý hệ điều hành',3),('CT179','','Cơ sở ngành','','Quản trị hệ thống',3),('CT180','CT177','Cơ sở ngành','','Cơ sở dữ liệu',3),('CT182','','Cơ sở ngành','','Ngôn ngữ mô hình hoá',3),('CT188','','Cơ sở ngành','','Nhập môn lập trình web',3),('CT189','','Chuyên ngành','','Nhập môn mô phỏng',3),('CT190','','Cơ sở ngành','','Nhập môn trí tuệ nhân tạo',2),('CT193','','Cơ sở ngành','','Kỹ thuật quay phim chụp hình và xử lý hậu kỳ',3),('CT194','','Cơ sở ngành','','Biên tập Audio Video',2),('CT196','','Cơ sở ngành','','Dựng hình 2D/3D',3),('CT197','CT177','Chuyên ngành','','Cơ sở lý thuyết mật mã',3),('CT198','','Chuyên ngành','','Anh văn chuyên ngành CNTT',3),('CT199','','Chuyên ngành','','Quy hoạch tuyến tính',3),('CT200','','Đại cương','','Nền tảng công nghệ thông tin',4),('CT201E','CT174, CT176','Cơ sở ngành','','Niên luận cơ sở ngành KHMT',3),('CT202','','Chuyên ngành','','Nguyên lý máy học',3),('CT203','CT176','Chuyên ngành','','Đồ họa máy tính',3),('CT204','','Chuyên ngành','','An toàn và bảo mật thông tin',3),('CT205','CT180','Chuyên ngành','','Quản trị cơ sở dữ liệu',3),('CT206','CT180,CT176','Chuyên ngành','','Phát triển ứng dụng trên Linux',3),('CT207','','Chuyên ngành','','Phát triển phần mềm mã nguồn mở',3),('CT208E','CT176, CT201','Chuyên ngành','','Niên luận ngành Khoa học máy tính',3),('CT209','CT203','Chuyên ngành','','Đồ họa nâng cao',3),('CT210','','Chuyên ngành','','Thị giác máy tính',3),('CT211','CT112','Chuyên ngành','','An ninh mạng',3),('CT212','CT112','Chuyên ngành','','Quản trị mạng',3),('CT216','CT332','Chuyên ngành','','Hệ cơ sở tri thức',3),('CT217','','Chuyên ngành','','Phân tích dữ liệu trực quan',3),('CT219','','Chuyên ngành','','Xử lý ngôn ngữ tự nhiên',3),('CT220','CT203','Chuyên ngành','','Hoạt hình trên máy tính',3),('CT221','CT112,CT176','Chuyên ngành','','Lập trình mạng',3),('CT222','','Chuyên ngành','','An toàn hệ thống',3),('CT223','CT171','Chuyên ngành','','Quản lý dự án phần mềm',3),('CT224','CT176','Chuyên ngành','','Công nghệ J2EE',2),('CT225','CT176','Chuyên ngành','','Lập trình Python',2),('CT226','','Chuyên ngành','','Niên luận cơ sở mạng máy tính và truyền thông',3),('CT227','','Chuyên ngành','','Kỹ thuật phát hiện tấn công mạng',3),('CT228','','Chuyên ngành','','Tường lửa',3),('CT229','','Chuyên ngành','','Bảo mật website',2),('CT230','CT428','Chuyên ngành','','Phát triển ứng dụng hướng dịch vụ',3),('CT231','','Chuyên ngành','','Lập trình song song',3),('CT232','CT112','Chuyên ngành','','Đánh giá hiệu năng mạng',3),('CT233','','Chuyên ngành','','Điện toán đám mây',3),('CT234','','Chuyên ngành','','Phát triển phần mềm nhúng',3),('CT235','CT112','Chuyên ngành','','Quản trị mạng trên MS Windows',3),('CT237','CT180','Chuyên ngành','','Nguyên lý hệ quản trị cơ sở dữ liệu',3),('CT238','','Chuyên ngành','','Phân lớp dữ liệu lớn',3),('CT239E','CT174','Chuyên ngành','','Niên luận cơ sở ngành KTPM',3),('CT240','CT113, CT176, CT182','Cơ sở ngành','','Nguyên lý xây dựng phần mềm',3),('CT241','CT113, CT182','Cơ sở ngành','','Phân tích yêu cầu phần mềm',3),('CT242','CT113','Cơ sở ngành','','Kiến trúc và Thiết kế phần mềm',3),('CT243','CT113','Chuyên ngành','','Đảm bảo chất lượng và Kiểm thử phần mềm',4),('CT244','CT113','Chuyên ngành','','Bảo trì phần mềm',3),('CT246','CT176','Chuyên ngành','','Lập trình .NET',3),('CT250E','CT241, CT242, CT243, CT223','Chuyên ngành','','Niên luận ngành Kỹ thuật phần mềm',3),('CT251','CT180,CT176','Chuyên ngành','','Phát triển ứng dụng trên Windows',3),('CT252E','CT180','Cơ sở ngành','','Niên luận cơ sở ngành HTTT',3),('CT254','CT296','Chuyên ngành','','Bảo mật, an toàn HTTT',3),('CT255','CT296','Chuyên ngành','','Nghiệp vụ thông minh (Business Intelligence)',3),('CT258','CT296','Chuyên ngành','','Phát triển hệ thống thương mại điện tử',3),('CT262','CT296','Chuyên ngành','','Phát triển hệ thống thông tin quản lý',3),('CT263E','CT296, CT430, CT291','Chuyên ngành','','Niên luận ngành HTTT',3),('CT265','CT180','Chuyên ngành','','Hệ CSDL đa phương tiện',3),('CT271E','CT174, CT176','Chuyên ngành','','Niên luận cơ sở - CNTT',3),('CT272','','Chuyên ngành','','Thương mại điện tử - CNTT',3),('CT273','','Chuyên ngành','','Giao diện người – máy',3),('CT274','CT176','Chuyên ngành','','Lập trình cho thiết bị di động',3),('CT275','CT180','Chuyên ngành','','Công nghệ Web',3),('CT276','CT176','Chuyên ngành','','Lập trình Java',3),('CT279','','Chuyên ngành','','Blockchain',3),('CT280','CT180','Chuyên ngành','','CSDL NoSQL',3),('CT281','CT180','Chuyên ngành','','Cơ sở dữ liệu phân tán',3),('CT282','CT294','Chuyên ngành','','Học sâu (Deep Learning)',3),('CT283','','Chuyên ngành','','Hệ thống chịu lỗi',3),('CT284','','Chuyên ngành','','Hệ thống hỏi đáp',3),('CT285','CT296','Chuyên ngành','','Hệ thống quản lý sản xuất',3),('CT286','CT180','Chuyên ngành','','Kho dữ liệu và OLAP',3),('CT287','CT241','Chuyên ngành','','Kiểm chứng mô hình',3),('CT288','','Chuyên ngành','','Kiến trúc phần mềm theo mô hình Client-Server',3),('CT290','','Chuyên ngành','','Lập trình trò chơi',3),('CT291','CT180','Cơ sở ngành','','Lập trình ứng dụng',3),('CT292','','Chuyên ngành','','Lý thuyết thông tin',3),('CT293','CT112','Cơ sở ngành','','Mạng và truyền thông dữ liệu',3),('CT294','','Chuyên ngành','','Máy học ứng dụng',3),('CT295','','Chuyên ngành','','Nền tảng phần mềm nhúng và IoT',3),('CT296','','Cơ sở ngành','','Phân tích và thiết kế hệ thống',3),('CT297','','Chuyên ngành','','Pháp y máy tính (CNTT)',3),('CT298E','CT296','Chuyên ngành','','Phát triển hệ thống thông tin địa lý',3),('CT299','CT180, CT188','Cơ sở ngành','','Phát triển hệ thống web',3),('CT300','CT176','Chuyên ngành','','Phát triển phần mềm',3),('CT312','','Chuyên ngành','','Khai khoáng dữ liệu',3),('CT316','CT176','Cơ sở ngành','','Xử lý ảnh',3),('CT332','','Chuyên ngành','','Trí tuệ nhân tạo',3),('CT335','CT112','Cơ sở ngành','','Thiết kế và cài đặt mạng',3),('CT338','CT112','Chuyên ngành','','Mạng không dây và di động',2),('CT344','CT335','Chuyên ngành','','Giải quyết sự cố mạng',2),('CT428','CT180, CT176','Chuyên ngành','','Lập trình Web',3),('CT430','CT182','Cơ sở ngành','','Phân tích hệ thống hướng đối tượng',3),('CT4339','','','','Niên luận Mạng máy tính và truyền thông	',3),('CT439','','Chuyên ngành','','Niên luận mạng máy tính và truyền thông',3),('CT446','','Chuyên ngành','','Ngôn ngữ lập trình mô phỏng',3),('CT449','','Chuyên ngành','','Phát triển ứng dụng Web',3),('CT457','','Chuyên ngành','','Phát triển phần mềm nhúng và IoT',3),('CT460','CT176','Chuyên ngành','','Quản lý quy trình nghiệp vụ',3),('CT466E','','Chuyên ngành','','Niên luận - CNTT',3),('CT467','CT180','Chuyên ngành','','Quản trị dữ liệu',3),('CT476','CT428,CT296,CT112','Chuyên ngành','','Thực tập thực tế - TT&MMT',3),('CT478','','Chuyên ngành','','Trung tâm dữ liệu',3),('CT479','','Chuyên ngành','','Phương pháp tính',3),('CT482','CT176','Chuyên ngành','','Xử lý dữ liệu lớn',3),('CT483','CT176','Chuyên ngành','','Chuyên đề lập trình trên di động',3),('CT484','','Chuyên ngành','','Phát triển ứng dụng di động',3),('CT485','CT112','Chuyên ngành','','Các kỹ thuật tấn công mạng',3),('CT486','CT112','Chuyên ngành','','Phát hiện và phân tích mã độc',3),('CT487','CT190','Chuyên ngành','','Học sâu cho công nghệ phần mềm',3),('CT488','CT112','Chuyên ngành','','Bảo mật hệ thống IoT',2),('CT489','','Chuyên ngành','','Luật an ninh mạng và đạo đức trong lĩnh vực CNTT',2),('CT490','CT428','Chuyên ngành','','An ninh Web',3),('CT491E','','Chuyên ngành','','Niên luận cơ sở an toàn thông tin',3),('CT493E','CT211,CT296,CT335,CT490','Chuyên ngành','','Thực tập doanh nghiệp - An toàn thông tin',5),('CT495','','Cơ sở ngành','','Dựng phim hoạt hình',3),('CT496','','Cơ sở ngành','','Kịch bản truyền hình ',3),('CT499','','Cơ sở ngành','','Thiết kế UI/UX',3),('CT501E','','Chuyên ngành','','Tiểu luận tốt nghiệp – CNTT',6),('CT503E','125 TC,CT296,CT430','Chuyên ngành','','Tiểu luận tốt nghiệp - HTTT',6),('CT504E','125TC','Chuyên ngành','','Tiểu luận tốt nghiệp - KHMT',6),('CT505E','','Chuyên ngành','','Tiểu luận tốt nghiệp - KTPM',6),('CT507','','Chuyên ngành','','Tiểu luận tốt nghiệp - TT&MMT',6),('CT509','','Cơ sở ngành','','Truyền thông trên Internet',3),('CT511E','125 TC,CT296,CT430','Chuyên ngành','','Thực tập doanh nghiệp - HTTT',5),('CT512','CT112','Chuyên ngành','','Điện toán đám mây và IoT',3),('CT513','CT296','Chuyên ngành','','Web ngữ nghĩa và ứng dụng',3),('CT514','CT180','Chuyên ngành','','Dữ liệu lớn và Chuyển đổi số',3),('CT515','CT190','Chuyên ngành','','Quản trị tri thức',3),('CT516E','125TC','Chuyên ngành','','Thực tập doanh nghiệp - KHMT',5),('CT518E','','Chuyên ngành','','Thực tập doanh nghiệp - CNTT',5),('CT520E','','Chuyên ngành','','Tiểu luận tốt nghiệp – An toàn thông tin',6),('CT522','','Chuyên ngành','','Nền tảng về internet vạn vật',3),('CT523','','Cơ sở ngành','','Phát triển ứng dụng Web đa dụng',3),('CT550E','','Chuyên ngành','','Luận văn tốt nghiệp – CNTT',15),('CT551E','125 TC,CT296,CT430','Chuyên ngành','','Luận văn tốt nghiệp – HTTT',15),('CT553E','','Chuyên ngành','','Luận văn tốt nghiệp - KTPM',15),('CT555','','Chuyên ngành','','Luận văn tốt nghiệp - TT&MMT',15),('FL001','','Đại cương','','Pháp văn căn bản 1',4),('FL002','FL001','Đại cương','','Pháp văn căn bản 2',3),('FL003','FL002','Đại cương','','Pháp văn căn bản 3',3),('FL007','FL003','Đại cương','','Pháp văn tăng cường 1',4),('FL008','FL007','Đại cương','','Pháp văn tăng cường 2',3),('FL009','FL008','Đại cương','','Pháp văn tăng cường 3',3),('KL001','','Đại cương','','Pháp luật đại cương',2),('KL100','TC100','Đại cương','','Pháp luật và đạo đức báo chí truyền thông',2),('KN001','','Đại cương','','Kỹ năng mềm',2),('KN002','','Đại cương','','Đổi mới sáng tạo và khởi nghiệp',2),('ML007','','Đại cương','','Logic học đại cương',2),('ML014','','Đại cương','','Triết học Mác - Lênin',3),('ML016','ML014','Đại cương','','Kinh tế chính trị Mác - Lênin',2),('ML018','ML016','Đại cương','','Chủ nghĩa xã hội khoa học',2),('ML019','ML018','Đại cương','','Lịch sử Đảng Cộng sản Việt Nam',2),('ML021','ML019','Đại cương','','Tư tưởng Hồ Chí Minh',2),('QP010','','Đại cương','','Giáo dục quốc phòng và An ninh 1',2),('QP011','','Đại cương','','Giáo dục quốc phòng và An ninh 2',2),('QP012','','Đại cương','','Giáo dục quốc phòng và An ninh 3',2),('QP013','','Đại cương','','Giáo dục quốc phòng và An ninh 4',2),('TC010','TC009','Đại cương','','Bóng bàn 2',1),('TC011','','Đại cương','','Cầu lông 1',1),('TC012','TC011','Đại cương','','Cầu lông 2',1),('TC013','','Đại cương','','Bơi lội',1),('TC016','','Đại cương','','Thể dục nhịp điệu 1',1),('TC017','TC016','Đại cương','','Thể dục nhịp điệu 2',1),('TC018','TC017','Đại cương','','Thể dục nhịp điệu 3',1),('TC022','TC010','Đại cương','','Bóng bàn 3',1),('TC023','TC012','Đại cương','','Cầu lông 3',1),('TC025','','Đại cương','','Sức khỏe 1',1),('TC026','TC025','Đại cương','','Sức khỏe 2',1),('TC027','TC026','Đại cương','','Sức khỏe 3',1),('TC028','','Đại cương','','Bóng rổ 1',1),('TC029','TC028','Đại cương','','Bóng rổ 2',1),('TC030','TC029','Đại cương','','Bóng rổ 3',1),('TC100','','Đại cương','','Giáo dục thể chất 1+2+3',3),('TN001','','Đại cương','','Vi - Tích phân A1',3),('TN002','TN001','Đại cương','','Vi - Tích phân A2',4),('TN010','','Đại cương','','Xác suất thống kê',3),('TN012','','Đại cương','','Đại số tuyến tính và hình học',4),('XH011','','Đại cương','','Cơ sở văn hóa Việt Nam',2),('XH011E','','Đại cương','','Cơ sở văn hóa Việt Nam',2),('XH012','','Đại cương','','Tiếng Việt thực hành',2),('XH014','','Đại cương','','Văn bản và lưu trữ học đại cương',2),('XH016','','Đại cương','','Mỹ học đại cương',2),('XH023','','Đại cương','','Anh văn căn bản 1',4),('XH024','XH023','Đại cương','','Anh văn căn bản 2',3),('XH025','XH024','Đại cương','','Anh văn căn bản 3',3),('XH028','','Đại cương','','Xã hội học đại cương',2),('XH031','XH025','Đại cương','','Anh văn tăng cường 1',4),('XH032','XH031','Đại cương','','Anh văn tăng cường 2',3),('XH033','XH032','Đại cương','','Anh văn tăng cường 3',3),('XH446','','Cơ sở ngành','','Tổ chức sự kiện',3),('XH447','','Đại cương','','Quan hệ công chúng',2);
/*!40000 ALTER TABLE `hoc_phan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_phan_tu_chon`
--

DROP TABLE IF EXISTS `hoc_phan_tu_chon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_phan_tu_chon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten_nhom` varchar(255) DEFAULT NULL,
  `tin_chi_yeu_cau` bigint DEFAULT NULL,
  `ma_ctdt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc2c3em39x5891g3lq2jn17tbf` (`ma_ctdt`),
  CONSTRAINT `FKc2c3em39x5891g3lq2jn17tbf` FOREIGN KEY (`ma_ctdt`) REFERENCES `chuong_trinh_dao_tao` (`khoa_hoc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_phan_tu_chon`
--

LOCK TABLES `hoc_phan_tu_chon` WRITE;
/*!40000 ALTER TABLE `hoc_phan_tu_chon` DISABLE KEYS */;
INSERT INTO `hoc_phan_tu_chon` VALUES (1,'Nhóm Chuyên Ngành',6,'K50'),(2,'Nhóm Chuyên Ngành - CN1',9,'K50'),(3,'Nhóm Chuyên Ngành - CN2',6,'K50');
/*!40000 ALTER TABLE `hoc_phan_tu_chon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_phan_tu_chon_hoc_phan`
--

DROP TABLE IF EXISTS `hoc_phan_tu_chon_hoc_phan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_phan_tu_chon_hoc_phan` (
  `id_nhom` bigint NOT NULL,
  `ma_hoc_phan` varchar(255) NOT NULL,
  KEY `FKdiiy117r1g7gdtt0d19pq8sl7` (`ma_hoc_phan`),
  KEY `FKrn0g1fspj2997itb0bsvxuger` (`id_nhom`),
  CONSTRAINT `FKdiiy117r1g7gdtt0d19pq8sl7` FOREIGN KEY (`ma_hoc_phan`) REFERENCES `hoc_phan` (`ma_hp`),
  CONSTRAINT `FKrn0g1fspj2997itb0bsvxuger` FOREIGN KEY (`id_nhom`) REFERENCES `hoc_phan_tu_chon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_phan_tu_chon_hoc_phan`
--

LOCK TABLES `hoc_phan_tu_chon_hoc_phan` WRITE;
/*!40000 ALTER TABLE `hoc_phan_tu_chon_hoc_phan` DISABLE KEYS */;
INSERT INTO `hoc_phan_tu_chon_hoc_phan` VALUES (1,'CT126'),(1,'CT127'),(1,'CT479'),(1,'CT121'),(1,'CT121'),(1,'CT224'),(1,'CT274'),(2,'CT228'),(2,'CT229'),(2,'CT222'),(2,'CT344'),(2,'CT232'),(3,'CT207'),(3,'CT231'),(3,'CT230'),(3,'CT233'),(3,'CT482');
/*!40000 ALTER TABLE `hoc_phan_tu_chon_hoc_phan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invalidated_token`
--

DROP TABLE IF EXISTS `invalidated_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invalidated_token` (
  `id` varchar(255) NOT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invalidated_token`
--

LOCK TABLES `invalidated_token` WRITE;
/*!40000 ALTER TABLE `invalidated_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `invalidated_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ke_hoach_hoc_tap`
--

DROP TABLE IF EXISTS `ke_hoach_hoc_tap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ke_hoach_hoc_tap` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hoc_phan_cai_thien` bit(1) NOT NULL,
  `ma_hoc_ky` bigint DEFAULT NULL,
  `ma_hoc_phan` varchar(255) DEFAULT NULL,
  `ma_so` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ke_hoach_hoc_tap`
--

LOCK TABLES `ke_hoach_hoc_tap` WRITE;
/*!40000 ALTER TABLE `ke_hoach_hoc_tap` DISABLE KEYS */;
/*!40000 ALTER TABLE `ke_hoach_hoc_tap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ket_qua_hoc_tap`
--

DROP TABLE IF EXISTS `ket_qua_hoc_tap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ket_qua_hoc_tap` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diem_chu` varchar(255) DEFAULT NULL,
  `diem_so` double DEFAULT NULL,
  `dieu_kien` bit(1) NOT NULL,
  `ma_hoc_ky` bigint DEFAULT NULL,
  `ma_hp` varchar(255) DEFAULT NULL,
  `ma_nhomhp` bigint DEFAULT NULL,
  `ma_so` varchar(255) DEFAULT NULL,
  `so_tin_chi` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ket_qua_hoc_tap`
--

LOCK TABLES `ket_qua_hoc_tap` WRITE;
/*!40000 ALTER TABLE `ket_qua_hoc_tap` DISABLE KEYS */;
/*!40000 ALTER TABLE `ket_qua_hoc_tap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa`
--

DROP TABLE IF EXISTS `khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoa` (
  `ma_khoa` bigint NOT NULL AUTO_INCREMENT,
  `ten_khoa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES (1,'Mạng máy tính và Truyền thông dữ liệu'),(2,'An toàn Thông tin'),(4,'Hệ thống Thông tin'),(5,'Khoa học Máy tính'),(6,'Kỹ thuật Phần mềm'),(7,'Truyền thông Đa phương tiện');
/*!40000 ALTER TABLE `khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `ma_lop` varchar(255) NOT NULL,
  `chu_nhiem` varchar(255) DEFAULT NULL,
  `ten_lop` varchar(255) DEFAULT NULL,
  `ma_nganh` bigint DEFAULT NULL,
  PRIMARY KEY (`ma_lop`),
  KEY `FKfnqo95o7qnn3i8a0p3hqyxom` (`ma_nganh`),
  CONSTRAINT `FKfnqo95o7qnn3i8a0p3hqyxom` FOREIGN KEY (`ma_nganh`) REFERENCES `nganh` (`ma_nganh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES ('DI24T9A1',NULL,'Mạng máy tính và Truyền thông dữ liệu A1',6);
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nam_hoc`
--

DROP TABLE IF EXISTS `nam_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nam_hoc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nam_bat_dau` varchar(255) DEFAULT NULL,
  `nam_ket_thuc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nam_hoc`
--

LOCK TABLES `nam_hoc` WRITE;
/*!40000 ALTER TABLE `nam_hoc` DISABLE KEYS */;
INSERT INTO `nam_hoc` VALUES (1,'2021','2022'),(2,'2022','2023'),(3,'2023','2024');
/*!40000 ALTER TABLE `nam_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nganh`
--

DROP TABLE IF EXISTS `nganh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nganh` (
  `ma_nganh` bigint NOT NULL AUTO_INCREMENT,
  `ten_nganh` varchar(255) DEFAULT NULL,
  `ma_khoa` bigint DEFAULT NULL,
  PRIMARY KEY (`ma_nganh`),
  KEY `FKeljm0njcqiwey0kgb6k487rk3` (`ma_khoa`),
  CONSTRAINT `FKeljm0njcqiwey0kgb6k487rk3` FOREIGN KEY (`ma_khoa`) REFERENCES `khoa` (`ma_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganh`
--

LOCK TABLES `nganh` WRITE;
/*!40000 ALTER TABLE `nganh` DISABLE KEYS */;
INSERT INTO `nganh` VALUES (1,'Truyền thông Đa phương tiện',7),(2,'Kỹ thuật Phần mềm',6),(3,'Khoa học Máy tính',5),(4,'Hệ thống Thông tin',4),(5,'An toàn Thông tin',2),(6,'Mạng máy tính và Truyền thông dữ liệu',1);
/*!40000 ALTER TABLE `nganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhom_hoc_phan`
--

DROP TABLE IF EXISTS `nhom_hoc_phan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhom_hoc_phan` (
  `ma_nhomhp` varchar(255) NOT NULL,
  `ma_giang_vien` varchar(255) DEFAULT NULL,
  `si_so` int NOT NULL,
  `si_so_con` int NOT NULL,
  `tiet_batdau` int NOT NULL,
  `tiet_ketthuc` int NOT NULL,
  `ma_hoc_ky` bigint DEFAULT NULL,
  `ma_hoc_phan` varchar(255) DEFAULT NULL,
  `ma_phong_hoc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_nhomhp`),
  KEY `FKa2iq8qqt3bl35yav6qpnrjxho` (`ma_hoc_ky`),
  KEY `FKlvigupo5l39xanfquxc3f7eid` (`ma_hoc_phan`),
  KEY `FKnxcoxv4k0w1r678d8mhorlpm2` (`ma_phong_hoc`),
  CONSTRAINT `FKa2iq8qqt3bl35yav6qpnrjxho` FOREIGN KEY (`ma_hoc_ky`) REFERENCES `hoc_ky` (`ma_hoc_ky`),
  CONSTRAINT `FKlvigupo5l39xanfquxc3f7eid` FOREIGN KEY (`ma_hoc_phan`) REFERENCES `hoc_phan` (`ma_hp`),
  CONSTRAINT `FKnxcoxv4k0w1r678d8mhorlpm2` FOREIGN KEY (`ma_phong_hoc`) REFERENCES `phong_hoc` (`ma_phong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhom_hoc_phan`
--

LOCK TABLES `nhom_hoc_phan` WRITE;
/*!40000 ALTER TABLE `nhom_hoc_phan` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhom_hoc_phan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_hoc`
--

DROP TABLE IF EXISTS `phong_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_hoc` (
  `ma_phong` varchar(255) NOT NULL,
  `ma_nha_hoc` varchar(255) DEFAULT NULL,
  `suc_chua` int NOT NULL,
  `tiet_hoc` bit(1) NOT NULL,
  PRIMARY KEY (`ma_phong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_hoc`
--

LOCK TABLES `phong_hoc` WRITE;
/*!40000 ALTER TABLE `phong_hoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `phong_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN'),('CHUNHIEM'),('GIANGVIEN'),('SINHVIEN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinh_vien`
--

DROP TABLE IF EXISTS `sinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinh_vien` (
  `ma_so` varchar(255) NOT NULL,
  `khoa_hoc` varchar(255) DEFAULT NULL,
  `ma_lop` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_so`),
  KEY `FKd6wx9fjodxfkjcdmm3biqagie` (`ma_lop`),
  CONSTRAINT `FKd6wx9fjodxfkjcdmm3biqagie` FOREIGN KEY (`ma_lop`) REFERENCES `lop` (`ma_lop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinh_vien`
--

LOCK TABLES `sinh_vien` WRITE;
/*!40000 ALTER TABLE `sinh_vien` DISABLE KEYS */;
INSERT INTO `sinh_vien` VALUES ('B2410945','K50','DI24T9A1');
/*!40000 ALTER TABLE `sinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ma_so` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gioi_tinh` bit(1) NOT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_so`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('B2410945','nguyenluong@gmail.com',_binary '\0','Nguyen Hải Lương','2003-11-04','$2a$10$3PINS/5.2Pnpkm5In0w5a./olfUp.EAjqG6haeblWAUOOxztKAEuK');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_ma_so` varchar(255) NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_ma_so`,`roles_name`),
  KEY `FK6pmbiap985ue1c0qjic44pxlc` (`roles_name`),
  CONSTRAINT `FK6pmbiap985ue1c0qjic44pxlc` FOREIGN KEY (`roles_name`) REFERENCES `role` (`name`),
  CONSTRAINT `FKcnvn8bb2q29t387ehpbcrbon4` FOREIGN KEY (`user_ma_so`) REFERENCES `user` (`ma_so`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('B2410945','SINHVIEN');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23 11:37:23
