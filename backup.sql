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
INSERT INTO `admin` VALUES ('C2110945','123123123','123123123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `ngay_cap` date DEFAULT NULL,
  `ten_chung_chi` varchar(255) DEFAULT NULL,
  `ma_so` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyw2yjwwweg7u01locxwase7j` (`ma_so`),
  CONSTRAINT `FKjyw2yjwwweg7u01locxwase7j` FOREIGN KEY (`ma_so`) REFERENCES `sinh_vien` (`ma_so`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tong_so_tin_chi` bigint DEFAULT NULL,
  `tong_so_tin_chi_tu_chon` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuong_trinh_dao_tao`
--

LOCK TABLES `chuong_trinh_dao_tao` WRITE;
/*!40000 ALTER TABLE `chuong_trinh_dao_tao` DISABLE KEYS */;
INSERT INTO `chuong_trinh_dao_tao` VALUES ('K47',7,'Không có',1,156,45),('K47',2,'Không có',5,156,45),('K47',6,'Không có',6,156,45),('K46',6,'Không có',7,156,45);
/*!40000 ALTER TABLE `chuong_trinh_dao_tao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctdt_hp`
--

DROP TABLE IF EXISTS `ctdt_hp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctdt_hp` (
  `chuong_trinh_dao_tao_id` bigint NOT NULL,
  `hoc_phan_id` varchar(255) NOT NULL,
  KEY `FKc8aatkkhr88qs9i1qjiyxn3i2` (`hoc_phan_id`),
  KEY `FKkovxqtj6uvla55pci4g827l4c` (`chuong_trinh_dao_tao_id`),
  CONSTRAINT `FKc8aatkkhr88qs9i1qjiyxn3i2` FOREIGN KEY (`hoc_phan_id`) REFERENCES `hoc_phan` (`ma_hp`),
  CONSTRAINT `FKkovxqtj6uvla55pci4g827l4c` FOREIGN KEY (`chuong_trinh_dao_tao_id`) REFERENCES `chuong_trinh_dao_tao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctdt_hp`
--

LOCK TABLES `ctdt_hp` WRITE;
/*!40000 ALTER TABLE `ctdt_hp` DISABLE KEYS */;
INSERT INTO `ctdt_hp` VALUES (1,'CT100'),(1,'CT101'),(1,'CT112'),(1,'CT121'),(1,'CT124'),(1,'CT126'),(1,'CT127'),(1,'CT172'),(1,'CT173'),(1,'CT174'),(1,'CT175'),(1,'CT176'),(1,'CT177'),(1,'CT178'),(1,'CT179'),(1,'CT180'),(1,'CT182'),(1,'CT188'),(1,'CT190'),(1,'CT200'),(1,'CT202'),(1,'CT205'),(1,'CT206'),(1,'CT207'),(1,'CT211'),(1,'CT212'),(1,'CT221'),(1,'CT222'),(1,'CT223'),(1,'CT224'),(1,'CT225'),(1,'CT226'),(1,'CT227'),(1,'CT228'),(1,'CT229'),(1,'CT230'),(1,'CT231'),(1,'CT232'),(1,'CT233'),(1,'CT234'),(1,'CT235'),(1,'CT237'),(1,'CT238'),(1,'CT251'),(1,'CT272'),(1,'CT273'),(1,'CT274'),(1,'CT296'),(1,'CT332'),(1,'CT335'),(1,'CT338'),(1,'CT344'),(1,'CT428'),(1,'CT439'),(1,'CT476'),(1,'CT482'),(1,'CT507'),(1,'CT555'),(1,'FL001'),(1,'FL002'),(1,'FL003'),(1,'KL001'),(1,'KN001'),(1,'KN002'),(1,'ML007'),(1,'ML014'),(1,'ML016'),(1,'ML018'),(1,'ML019'),(1,'ML021'),(1,'QP011'),(1,'QP012'),(1,'QP013'),(1,'TN001'),(1,'TN002'),(1,'TN010'),(1,'TN012'),(1,'XH011'),(1,'XH012'),(1,'XH014'),(1,'XH023'),(1,'XH024'),(1,'XH025'),(1,'XH028'),(1,'QP010'),(1,'CT100'),(1,'CT101'),(1,'CT112'),(1,'CT172'),(1,'CT173'),(1,'CT174'),(1,'CT175'),(1,'CT176'),(1,'CT177'),(1,'CT178'),(1,'CT179'),(1,'CT180'),(1,'CT182'),(1,'CT188'),(1,'CT190'),(1,'CT200'),(1,'CT210'),(1,'CT211'),(1,'CT219'),(1,'CT222'),(1,'CT223'),(1,'CT233'),(1,'CT273'),(1,'CT275'),(1,'CT279'),(1,'CT283'),(1,'CT293'),(1,'CT294'),(1,'CT296'),(1,'CT297'),(1,'CT300'),(1,'CT312'),(1,'CT449'),(1,'CT467'),(1,'CT478'),(1,'CT482'),(1,'CT484'),(1,'CT522'),(1,'FL001'),(1,'FL002'),(1,'FL003'),(1,'FL007'),(1,'FL008'),(1,'FL009'),(1,'KL001'),(1,'KN001'),(1,'KN002'),(1,'ML007'),(1,'ML014'),(1,'ML016'),(1,'ML018'),(1,'ML019'),(1,'ML021'),(1,'QP010'),(1,'QP011'),(1,'QP012'),(1,'QP013'),(1,'TN001'),(1,'TN002'),(1,'TN010'),(1,'TN012'),(1,'XH011'),(1,'XH012'),(1,'XH014'),(1,'XH023'),(1,'XH024'),(1,'XH025'),(1,'XH028'),(1,'XH031'),(1,'XH032'),(1,'XH033'),(6,'CT100'),(6,'CT101'),(6,'CT112'),(6,'CT121'),(6,'CT124'),(6,'CT126'),(6,'CT127'),(6,'CT172'),(6,'CT173'),(6,'CT174'),(6,'CT175'),(6,'CT176'),(6,'CT177'),(6,'CT178'),(6,'CT179'),(6,'CT180'),(6,'CT182'),(6,'CT188'),(6,'CT190'),(6,'CT200'),(6,'CT202'),(6,'CT205'),(6,'CT206'),(6,'CT207'),(6,'CT211'),(6,'CT212'),(6,'CT221'),(6,'CT222'),(6,'CT223'),(6,'CT224'),(6,'CT225'),(6,'CT226'),(6,'CT227'),(6,'CT228'),(6,'CT229'),(6,'CT230'),(6,'CT231'),(6,'CT232'),(6,'CT233'),(6,'CT234'),(6,'CT235'),(6,'CT237'),(6,'CT238'),(6,'CT251'),(6,'CT272'),(6,'CT273'),(6,'CT274'),(6,'CT296'),(6,'CT332'),(6,'CT335'),(6,'CT338'),(6,'CT344'),(6,'CT428'),(6,'CT439'),(6,'CT476'),(6,'CT482'),(6,'CT507'),(6,'CT555'),(6,'FL001'),(6,'FL002'),(6,'FL003'),(6,'KL001'),(6,'KN001'),(6,'KN002'),(6,'ML007'),(6,'ML014'),(6,'ML016'),(6,'ML018'),(6,'ML019'),(6,'ML021'),(6,'QP010'),(6,'QP011'),(6,'QP012'),(6,'QP013'),(6,'TN001'),(6,'TN002'),(6,'TN010'),(6,'TN012'),(6,'XH011'),(6,'XH012'),(6,'XH014'),(6,'XH023'),(6,'XH024'),(6,'XH025'),(6,'XH028'),(1,'CT466'),(1,'CT550'),(7,'CT100'),(7,'CT101'),(7,'CT112'),(7,'CT121'),(7,'CT124'),(7,'CT126'),(7,'CT127'),(7,'CT172'),(7,'CT173'),(7,'CT174'),(7,'CT175'),(7,'CT176'),(7,'CT177'),(7,'CT178'),(7,'CT179'),(7,'CT180'),(7,'CT182'),(7,'CT188'),(7,'CT190'),(7,'CT200'),(7,'CT202'),(7,'CT205'),(7,'CT206'),(7,'CT207'),(7,'CT211'),(7,'CT212'),(7,'CT221'),(7,'CT222'),(7,'CT223'),(7,'CT224'),(7,'CT225'),(7,'CT226'),(7,'CT227'),(7,'CT228'),(7,'CT229'),(7,'CT230'),(7,'CT231'),(7,'CT232'),(7,'CT233'),(7,'CT234'),(7,'CT235'),(7,'CT237'),(7,'CT238'),(7,'CT251'),(7,'CT272'),(7,'CT273'),(7,'CT274'),(7,'CT296'),(7,'CT332'),(7,'CT335'),(7,'CT338'),(7,'CT344'),(7,'CT428'),(7,'CT439'),(7,'CT476'),(7,'CT482'),(7,'CT507'),(7,'CT555'),(7,'FL001'),(7,'FL002'),(7,'FL003'),(7,'KL001'),(7,'KN001'),(7,'KN002'),(7,'ML007'),(7,'ML014'),(7,'ML016'),(7,'ML018'),(7,'ML019'),(7,'ML021'),(7,'TN001'),(7,'TN002'),(7,'TN010'),(7,'TN012'),(7,'XH011'),(7,'XH012'),(7,'XH014'),(7,'XH023'),(7,'XH024'),(7,'XH025'),(7,'XH028'),(7,'TC001'),(7,'TC002'),(7,'TC004'),(7,'TC005'),(7,'TC006'),(7,'TC007'),(7,'TC008'),(7,'TC009'),(7,'TC010'),(7,'TC011'),(7,'TC012'),(7,'TC013'),(7,'TC016'),(7,'TC017'),(7,'TC018'),(7,'TC019'),(7,'TC020'),(7,'TC021'),(7,'TC022'),(7,'TC023'),(7,'TC024'),(7,'TC025'),(7,'TC026'),(7,'TC027'),(7,'TC028'),(7,'TC029'),(7,'TC030'),(7,'CT100'),(7,'CT101'),(7,'CT112'),(7,'CT121'),(7,'CT124'),(7,'CT126'),(7,'CT127'),(7,'CT172'),(7,'CT173'),(7,'CT174'),(7,'CT175'),(7,'CT176'),(7,'CT177'),(7,'CT178'),(7,'CT179'),(7,'CT180'),(7,'CT182'),(7,'CT188'),(7,'CT190'),(7,'CT200'),(7,'CT202'),(7,'CT205'),(7,'CT206'),(7,'CT207'),(7,'CT211'),(7,'CT212'),(7,'CT221'),(7,'CT222'),(7,'CT223'),(7,'CT224'),(7,'CT225'),(7,'CT226'),(7,'CT227'),(7,'CT228'),(7,'CT229'),(7,'CT230'),(7,'CT231'),(7,'CT232'),(7,'CT233'),(7,'CT234'),(7,'CT235'),(7,'CT237'),(7,'CT238'),(7,'CT251'),(7,'CT272'),(7,'CT273'),(7,'CT274'),(7,'CT296'),(7,'CT332'),(7,'CT335'),(7,'CT338'),(7,'CT344'),(7,'CT428'),(7,'CT439'),(7,'CT476'),(7,'CT482'),(7,'CT507'),(7,'CT555'),(7,'FL001'),(7,'FL002'),(7,'FL003'),(7,'KL001'),(7,'KN001'),(7,'KN002'),(7,'ML007'),(7,'ML014'),(7,'ML016'),(7,'ML018'),(7,'ML019'),(7,'ML021'),(7,'TN001'),(7,'TN002'),(7,'TN010'),(7,'TN012'),(7,'XH011'),(7,'XH012'),(7,'XH014'),(7,'XH023'),(7,'XH024'),(7,'XH025'),(7,'XH028'),(7,'TC001'),(7,'TC002'),(7,'TC004'),(7,'TC005'),(7,'TC006'),(7,'TC007'),(7,'TC008'),(7,'TC009'),(7,'TC010'),(7,'TC011'),(7,'TC012'),(7,'TC013'),(7,'TC016'),(7,'TC017'),(7,'TC018'),(7,'TC019'),(7,'TC020'),(7,'TC021'),(7,'TC022'),(7,'TC023'),(7,'TC024'),(7,'TC025'),(7,'TC026'),(7,'TC027'),(7,'TC028'),(7,'TC029'),(7,'TC030'),(7,'QP010'),(7,'QP011'),(7,'QP012'),(7,'QP013'),(6,'TC100');
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
INSERT INTO `giang_vien` VALUES ('C2110945',123123123,123123123,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_ky`
--

LOCK TABLES `hoc_ky` WRITE;
/*!40000 ALTER TABLE `hoc_ky` DISABLE KEYS */;
INSERT INTO `hoc_ky` VALUES (1,'2021-09-01','2022-01-18','Học Kỳ 1',1),(2,'2022-01-13','2022-05-31','Học Kỳ 2',1),(3,'2022-06-01','2022-08-31','Học Kỳ 3',1),(4,'2023-09-01','2023-01-18','Học Kỳ 1',2),(5,'2023-01-13','2023-05-31','Học Kỳ 2',2),(6,'2023-05-01','2023-08-31','Học Kỳ 3',2),(7,NULL,NULL,'Học Kỳ 1',3),(8,NULL,NULL,'Học Kỳ 2',3),(9,NULL,NULL,'Học Kỳ 3',3),(10,NULL,NULL,'Học Kỳ 1',4),(11,NULL,NULL,'Học Kỳ 2',4),(12,NULL,NULL,'Học Kỳ 3',4),(13,NULL,NULL,'Học Kỳ 1',5),(14,NULL,NULL,'Học Kỳ 2',5),(16,NULL,NULL,'Học Kỳ 3',5),(17,NULL,NULL,'Học Kỳ 1',6),(18,NULL,NULL,'Học Kỳ 2',6),(19,NULL,NULL,'Học Kỳ 3',6),(20,NULL,NULL,'Học Kỳ 1',7),(21,NULL,NULL,'Học Kỳ 2',7),(22,NULL,NULL,'Học Kỳ 3',7),(23,NULL,NULL,'Học Kỳ 1',8),(24,NULL,NULL,'Học Kỳ 2',8),(25,NULL,NULL,'Học Kỳ 3',8);
/*!40000 ALTER TABLE `hoc_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_ky_hien_tai`
--

DROP TABLE IF EXISTS `hoc_ky_hien_tai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_ky_hien_tai` (
  `ma_hoc_ky` bigint NOT NULL,
  PRIMARY KEY (`ma_hoc_ky`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_ky_hien_tai`
--

LOCK TABLES `hoc_ky_hien_tai` WRITE;
/*!40000 ALTER TABLE `hoc_ky_hien_tai` DISABLE KEYS */;
INSERT INTO `hoc_ky_hien_tai` VALUES (12);
/*!40000 ALTER TABLE `hoc_ky_hien_tai` ENABLE KEYS */;
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
INSERT INTO `hoc_phan` VALUES ('CT083','','Đại cương','','Ngôn ngữ truyền thông',2),('CT084','','Đại cương','','Cơ sở kỹ thuật đồ họa',2),('CT085','','Cơ sở ngành','','Nhập môn Truyền thông đa phương tiện',3),('CT086','','Cơ sở ngành','','Nhập môn Web Đa phương tiện',3),('CT087','','Cơ sở ngành','','Nền tảng Kỹ xảo số',3),('CT088','','Cơ sở ngành','','Nhập môn Tiếp thị kỹ thuật số',3),('CT089','CT086','Cơ sở ngành','','Nhập môn Lập trình đa phương tiện',4),('CT092','','Cơ sở ngành','','Kỹ thuật dựng phim ảnh kỹ thuật số',3),('CT093','','Cơ sở ngành','','Kỹ xảo số',3),('CT094','','Cơ sở ngành','','Quy trình sáng tạo sản phẩm đa phương tiện',3),('CT095','','Cơ sở ngành','','Dự án phim hoạt hình 3D',3),('CT096','','Cơ sở ngành','','Tiếp thị nội dung',3),('CT097','','Cơ sở ngành','','Phát triển ứng dụng Web',3),('CT098','','Chuyên ngành','','Phát triển ứng dụng IoT',3),('CT099','CT296','Chuyên ngành','','Blockchain và ứng dụng',3),('CT100','','Đại cương','','Kỹ năng học đại học',2),('CT101','','Cơ sở ngành','','Lập trình căn bản A',4),('CT112','CT178','Cơ sở ngành','','Mạng máy tính',3),('CT113','','Cơ sở ngành','','Nhập môn công nghệ phần mềm',2),('CT121','CT101','Chuyên ngành','','Tin học lý thuyết',3),('CT124','','Cơ sở ngành','','Phương pháp tính – CNTT',2),('CT126','','Chuyên ngành','','Lý thuyết xếp hàng',2),('CT127','','Chuyên ngành','','Lý thuyết thông tin',2),('CT172','','Cơ sở ngành','','Toán rời rạc',4),('CT173','','Cơ sở ngành','','Kiến trúc máy tính',3),('CT174','CT177','Cơ sở ngành','','Phân tích và thiết kế thuật toán',3),('CT175','CT177','Cơ sở ngành','','Lý thuyết đồ thị',4),('CT176','CT101','Cơ sở ngành','','Lập trình hướng đối tượng',3),('CT177','','Cơ sở ngành','','Cấu trúc dữ liệu',3),('CT178','CT173','Cơ sở ngành','','Nguyên lý hệ điều hành',3),('CT179','','Cơ sở ngành','','Quản trị hệ thống',3),('CT180','CT177','Cơ sở ngành','','Cơ sở dữ liệu',3),('CT182','','Cơ sở ngành','','Ngôn ngữ mô hình hoá',3),('CT188','','Cơ sở ngành','','Nhập môn lập trình web',3),('CT189','','Chuyên ngành','','Nhập môn mô phỏng',3),('CT190','','Cơ sở ngành','','Nhập môn trí tuệ nhân tạo',2),('CT193','','Cơ sở ngành','','Kỹ thuật quay phim chụp hình và xử lý hậu kỳ',3),('CT194','','Cơ sở ngành','','Biên tập Audio Video',2),('CT196','','Cơ sở ngành','','Dựng hình 2D/3D',3),('CT197','CT177','Chuyên ngành','','Cơ sở lý thuyết mật mã',3),('CT198','','Chuyên ngành','','Anh văn chuyên ngành CNTT',3),('CT199','','Chuyên ngành','','Quy hoạch tuyến tính',3),('CT200','','Đại cương','','Nền tảng công nghệ thông tin',4),('CT201E','CT174,CT176','Cơ sở ngành','','Niên luận cơ sở ngành KHMT',3),('CT202','','Chuyên ngành','','Nguyên lý máy học',3),('CT203','CT176','Chuyên ngành','','Đồ họa máy tính',3),('CT204','','Chuyên ngành','','An toàn và bảo mật thông tin',3),('CT205','CT180','Chuyên ngành','','Quản trị cơ sở dữ liệu',3),('CT206','CT180,CT176','Chuyên ngành','','Phát triển ứng dụng trên Linux',3),('CT207','','Chuyên ngành','','Phát triển phần mềm mã nguồn mở',3),('CT208E','CT176,CT201','Chuyên ngành','','Niên luận ngành Khoa học máy tính',3),('CT209','CT203','Chuyên ngành','','Đồ họa nâng cao',3),('CT210','','Chuyên ngành','','Thị giác máy tính',3),('CT211','CT112','Chuyên ngành','','An ninh mạng',3),('CT212','CT112','Chuyên ngành','','Quản trị mạng',3),('CT216','CT332','Chuyên ngành','','Hệ cơ sở tri thức',3),('CT217','','Chuyên ngành','','Phân tích dữ liệu trực quan',3),('CT219','','Chuyên ngành','','Xử lý ngôn ngữ tự nhiên',3),('CT220','CT203','Chuyên ngành','','Hoạt hình trên máy tính',3),('CT221','CT112,CT176','Chuyên ngành','','Lập trình mạng',3),('CT222','','Chuyên ngành','','An toàn hệ thống',3),('CT223','CT171','Chuyên ngành','','Quản lý dự án phần mềm',3),('CT224','CT176','Chuyên ngành','','Công nghệ J2EE',2),('CT225','CT176','Chuyên ngành','','Lập trình Python',2),('CT226','','Chuyên ngành','','Niên luận cơ sở mạng máy tính và truyền thông',3),('CT227','','Chuyên ngành','','Kỹ thuật phát hiện tấn công mạng',3),('CT228','','Chuyên ngành','','Tường lửa',3),('CT229','','Chuyên ngành','','Bảo mật website',2),('CT230','CT428','Chuyên ngành','','Phát triển ứng dụng hướng dịch vụ',3),('CT231','','Chuyên ngành','','Lập trình song song',3),('CT232','CT112','Chuyên ngành','','Đánh giá hiệu năng mạng',3),('CT233','','Chuyên ngành','','Điện toán đám mây',3),('CT234','','Chuyên ngành','','Phát triển phần mềm nhúng',3),('CT235','CT112','Chuyên ngành','','Quản trị mạng trên MS Windows',3),('CT237','CT180','Chuyên ngành','','Nguyên lý hệ quản trị cơ sở dữ liệu',3),('CT238','','Chuyên ngành','','Phân lớp dữ liệu lớn',3),('CT239','CT174','Chuyên ngành','','Niên luận cơ sở ngành KTPM',3),('CT240','CT113,CT176,CT182','Cơ sở ngành','','Nguyên lý xây dựng phần mềm',3),('CT241','CT113, CT182','Cơ sở ngành','','Phân tích yêu cầu phần mềm',3),('CT242','CT113','Cơ sở ngành','','Kiến trúc và Thiết kế phần mềm',3),('CT243','CT113','Chuyên ngành','','Đảm bảo chất lượng và Kiểm thử phần mềm',4),('CT244','CT113','Chuyên ngành','','Bảo trì phần mềm',3),('CT246','CT176','Chuyên ngành','','Lập trình .NET',3),('CT250','CT241,CT242,CT243,CT223','Chuyên ngành','','Niên luận ngành Kỹ thuật phần mềm',3),('CT251','CT180,CT176','Chuyên ngành','','Phát triển ứng dụng trên Windows',3),('CT252','CT180','Cơ sở ngành','','Niên luận cơ sở ngành HTTT',3),('CT254','CT296','Chuyên ngành','','Bảo mật, an toàn HTTT',3),('CT255','CT296','Chuyên ngành','','Nghiệp vụ thông minh (Business Intelligence)',3),('CT258','CT296','Chuyên ngành','','Phát triển hệ thống thương mại điện tử',3),('CT262','CT296','Chuyên ngành','','Phát triển hệ thống thông tin quản lý',3),('CT263E','CT296,CT430,CT291','Chuyên ngành','','Niên luận ngành HTTT',3),('CT265','CT180','Chuyên ngành','','Hệ CSDL đa phương tiện',3),('CT271','','Chuyên ngành','','Niên luận cơ sở - CNTT',3),('CT272','','Chuyên ngành','','Thương mại điện tử - CNTT',3),('CT273','','Chuyên ngành','','Giao diện người – máy',3),('CT274','CT176','Chuyên ngành','','Lập trình cho thiết bị di động',3),('CT275','CT180','Chuyên ngành','','Công nghệ Web',3),('CT276','CT176','Chuyên ngành','','Lập trình Java',3),('CT279','','Chuyên ngành','','Blockchain',3),('CT280','CT180','Chuyên ngành','','CSDL NoSQL',3),('CT281','CT180','Chuyên ngành','','Cơ sở dữ liệu phân tán',3),('CT282','CT294','Chuyên ngành','','Học sâu (Deep Learning)',3),('CT283','','Chuyên ngành','','Hệ thống chịu lỗi',3),('CT284','','Chuyên ngành','','Hệ thống hỏi đáp',3),('CT285','CT296','Chuyên ngành','','Hệ thống quản lý sản xuất',3),('CT286','CT180','Chuyên ngành','','Kho dữ liệu và OLAP',3),('CT287','CT241','Chuyên ngành','','Kiểm chứng mô hình',3),('CT288','','Chuyên ngành','','Kiến trúc phần mềm theo mô hình Client-Server',3),('CT290','','Chuyên ngành','','Lập trình trò chơi',3),('CT291','CT180','Cơ sở ngành','','Lập trình ứng dụng',3),('CT292','','Chuyên ngành','','Lý thuyết thông tin',3),('CT293','CT112','Cơ sở ngành','','Mạng và truyền thông dữ liệu',3),('CT294','','Chuyên ngành','','Máy học ứng dụng',3),('CT295','','Chuyên ngành','','Nền tảng phần mềm nhúng và IoT',3),('CT296','','Cơ sở ngành','','Phân tích và thiết kế hệ thống',3),('CT297','','Chuyên ngành','','Pháp y máy tính (CNTT)',3),('CT298E','CT296','Chuyên ngành','','Phát triển hệ thống thông tin địa lý',3),('CT299','CT180,CT188','Cơ sở ngành','','Phát triển hệ thống web',3),('CT300','CT176','Chuyên ngành','','Phát triển phần mềm',3),('CT312','','Chuyên ngành','','Khai khoáng dữ liệu',3),('CT316','CT176','Cơ sở ngành','','Xử lý ảnh',3),('CT332','','Chuyên ngành','','Trí tuệ nhân tạo',3),('CT335','CT112','Cơ sở ngành','','Thiết kế và cài đặt mạng',3),('CT338','CT112','Chuyên ngành','','Mạng không dây và di động',2),('CT344','CT335','Chuyên ngành','','Giải quyết sự cố mạng',2),('CT428','CT180,CT176','Chuyên ngành','','Lập trình Web',3),('CT430','CT182','Cơ sở ngành','','Phân tích hệ thống hướng đối tượng',3),('CT4339','','','','Niên luận Mạng máy tính và truyền thông	',3),('CT439','','Chuyên ngành','','Niên luận mạng máy tính và truyền thông',3),('CT446','','Chuyên ngành','','Ngôn ngữ lập trình mô phỏng',3),('CT449','','Chuyên ngành','','Phát triển ứng dụng Web',3),('CT457','','Chuyên ngành','','Phát triển phần mềm nhúng và IoT',3),('CT460','CT176','Chuyên ngành','','Quản lý quy trình nghiệp vụ',3),('CT466','','Chuyên ngành','','Niên luận - CNTT',3),('CT467','CT180','Chuyên ngành','','Quản trị dữ liệu',3),('CT471',NULL,'Đại cương','','Thực Tập Thực Tế',2),('CT476','CT428,CT296,CT112','Chuyên ngành','','Thực tập thực tế - TT&MMT',3),('CT478','','Chuyên ngành','','Trung tâm dữ liệu',3),('CT479','','Chuyên ngành','','Phương pháp tính',3),('CT482','CT176','Chuyên ngành','','Xử lý dữ liệu lớn',3),('CT483','CT176','Chuyên ngành','','Chuyên đề lập trình trên di động',3),('CT484','','Chuyên ngành','','Phát triển ứng dụng di động',3),('CT485','CT112','Chuyên ngành','','Các kỹ thuật tấn công mạng',3),('CT486','CT112','Chuyên ngành','','Phát hiện và phân tích mã độc',3),('CT487','CT190','Chuyên ngành','','Học sâu cho công nghệ phần mềm',3),('CT488','CT112','Chuyên ngành','','Bảo mật hệ thống IoT',2),('CT489','','Chuyên ngành','','Luật an ninh mạng và đạo đức trong lĩnh vực CNTT',2),('CT490','CT428','Chuyên ngành','','An ninh Web',3),('CT491E','','Chuyên ngành','','Niên luận cơ sở an toàn thông tin',3),('CT493E','CT211,CT296,CT335,CT490','Chuyên ngành','','Thực tập doanh nghiệp - An toàn thông tin',5),('CT495','','Cơ sở ngành','','Dựng phim hoạt hình',3),('CT496','','Cơ sở ngành','','Kịch bản truyền hình ',3),('CT499','','Cơ sở ngành','','Thiết kế UI/UX',3),('CT501E','','Chuyên ngành','','Tiểu luận tốt nghiệp – CNTT',6),('CT503E','CT296,CT430','Chuyên ngành','','Tiểu luận tốt nghiệp - HTTT',6),('CT504E','125TC','Chuyên ngành','','Tiểu luận tốt nghiệp - KHMT',6),('CT505E','','Chuyên ngành','','Tiểu luận tốt nghiệp - KTPM',6),('CT507','','Chuyên ngành','','Tiểu luận tốt nghiệp - TT&MMT',6),('CT509','','Cơ sở ngành','','Truyền thông trên Internet',3),('CT511E','CT296,CT430','Chuyên ngành','','Thực tập doanh nghiệp - HTTT',5),('CT512','CT112','Chuyên ngành','','Điện toán đám mây và IoT',3),('CT513','CT296','Chuyên ngành','','Web ngữ nghĩa và ứng dụng',3),('CT514','CT180','Chuyên ngành','','Dữ liệu lớn và Chuyển đổi số',3),('CT515','CT190','Chuyên ngành','','Quản trị tri thức',3),('CT516','','Chuyên ngành','','Thực tập doanh nghiệp - KHMT',5),('CT518','','Chuyên ngành','','Thực tập doanh nghiệp - CNTT',5),('CT520','','Chuyên ngành','','Tiểu luận tốt nghiệp – An toàn thông tin',6),('CT522','','Chuyên ngành','','Nền tảng về internet vạn vật',3),('CT523','','Cơ sở ngành','','Phát triển ứng dụng Web đa dụng',3),('CT550','','Chuyên ngành','','Luận văn tốt nghiệp – CNTT',15),('CT551','CT296,CT430','Chuyên ngành','','Luận văn tốt nghiệp – HTTT',15),('CT553','','Chuyên ngành','','Luận văn tốt nghiệp - KTPM',15),('CT555','','Chuyên ngành','','Luận văn tốt nghiệp - TT&MMT',15),('FL001','','Đại cương','','Pháp văn căn bản 1',4),('FL002','FL001','Đại cương','','Pháp văn căn bản 2',3),('FL003','FL002','Đại cương','','Pháp văn căn bản 3',3),('FL007','FL003','Đại cương','','Pháp văn tăng cường 1',4),('FL008','FL007','Đại cương','','Pháp văn tăng cường 2',3),('FL009','FL008','Đại cương','','Pháp văn tăng cường 3',3),('KL001','','Đại cương','','Pháp luật đại cương',2),('KL100','TC100','Đại cương','','Pháp luật và đạo đức báo chí truyền thông',2),('KN001','','Đại cương','','Kỹ năng mềm',2),('KN002','','Đại cương','','Đổi mới sáng tạo và khởi nghiệp',2),('ML007','','Đại cương','','Logic học đại cương',2),('ML014','','Đại cương','','Triết học Mác - Lênin',3),('ML016','ML014','Đại cương','','Kinh tế chính trị Mác - Lênin',2),('ML018','ML016','Đại cương','','Chủ nghĩa xã hội khoa học',2),('ML019','ML018','Đại cương','','Lịch sử Đảng Cộng sản Việt Nam',2),('ML021','ML019','Đại cương','','Tư tưởng Hồ Chí Minh',2),('QP010','','Đại cương','','Giáo dục quốc phòng và An ninh 1',2),('QP011','','Đại cương','','Giáo dục quốc phòng và An ninh 2',2),('QP012','','Đại cương','','Giáo dục quốc phòng và An ninh 3',2),('QP013','','Đại cương','','Giáo dục quốc phòng và An ninh 4',2),('TC001','','Cơ sở ngành','','Điền Kinh 1',1),('TC002','','Cơ sở ngành',NULL,'Điền Kinh 2',1),('TC004','','Cơ sở ngành',NULL,'Taekwondo 2',1),('TC005','','Cơ sở ngành',NULL,'Bóng Chuyền 1',1),('TC006','','Cơ sở ngành',NULL,'Bóng Chuyền 2',1),('TC007','','Cơ sở ngành',NULL,'Bóng Đá 1',1),('TC008','','Cơ sở ngành',NULL,'Bóng Đá 2',1),('TC009','','Cơ sở ngành',NULL,'Bóng Bàn 1',1),('TC010','TC009','Đại cương','','Bóng bàn 2',1),('TC011','','Đại cương','','Cầu lông 1',1),('TC012','TC011','Đại cương','','Cầu lông 2',1),('TC013','','Đại cương','','Bơi lội',1),('TC016','','Đại cương','','Thể dục nhịp điệu 1',1),('TC017','TC016','Đại cương','','Thể dục nhịp điệu 2',1),('TC018','TC017','Đại cương','','Thể dục nhịp điệu 3',1),('TC019','','Cơ sở ngành',NULL,'Taekwondo 3',1),('TC020','','Cơ sở ngành',NULL,'Bóng Chuyền 3',1),('TC021','','Cơ sở ngành',NULL,'Bóng Đá 3',1),('TC022','TC010','Đại cương','','Bóng bàn 3',1),('TC023','TC012','Đại cương','','Cầu lông 3',1),('TC024','','Cơ sở ngành',NULL,'Điền Kinh 3',1),('TC025','','Đại cương','','Cờ vua 1 ',1),('TC026','TC025','Đại cương','','Cờ vua 2',1),('TC027','TC026','Đại cương','','Cờ vua 3',1),('TC028','','Đại cương','','Bóng rổ 1',1),('TC029','TC028','Đại cương','','Bóng rổ 2',1),('TC030','TC029','Đại cương','','Bóng rổ 3',1),('TC100',NULL,'Đại Cương',NULL,'Nhóm HP Thể chất',3),('TN001','','Đại cương','','Vi - Tích phân A1',3),('TN002','TN001','Đại cương','','Vi - Tích phân A2',4),('TN010','','Đại cương','','Xác suất thống kê',3),('TN012','','Đại cương','','Đại số tuyến tính và hình học',4),('XH011','','Đại cương','','Cơ sở văn hóa Việt Nam',2),('XH012','','Đại cương','','Tiếng Việt thực hành',2),('XH014','','Đại cương','','Văn bản và lưu trữ học đại cương',2),('XH016','','Đại cương','','Mỹ học đại cương',2),('XH023','','Đại cương','','Anh văn căn bản 1',4),('XH024','XH023','Đại cương','','Anh văn căn bản 2',3),('XH025','XH024','Đại cương','','Anh văn căn bản 3',3),('XH028','','Đại cương','','Xã hội học đại cương',2),('XH031','XH025','Đại cương','','Anh văn tăng cường 1',4),('XH032','XH031','Đại cương','','Anh văn tăng cường 2',3),('XH033','XH032','Đại cương','','Anh văn tăng cường 3',3),('XH446','','Cơ sở ngành','','Tổ chức sự kiện',3),('XH447','','Đại cương','','Quan hệ công chúng',2);
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
  `chuong_trinh_dao_tao_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqkqbam0x3bdsdnep4btdvdk2` (`chuong_trinh_dao_tao_id`),
  CONSTRAINT `FKqkqbam0x3bdsdnep4btdvdk2` FOREIGN KEY (`chuong_trinh_dao_tao_id`) REFERENCES `chuong_trinh_dao_tao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_phan_tu_chon`
--

LOCK TABLES `hoc_phan_tu_chon` WRITE;
/*!40000 ALTER TABLE `hoc_phan_tu_chon` DISABLE KEYS */;
INSERT INTO `hoc_phan_tu_chon` VALUES (1,'Nhóm Cơ Sở Ngành',6,6),(2,'Nhóm Chuyên Ngành - CN1',9,6),(3,'Nhóm Chuyên Ngành - CN2',6,6),(4,'Nhóm Thể Chất',3,6),(5,'Nhóm Chuyên Ngành - CN1',6,1),(6,'Nhóm Chuyên Ngành - CN2',6,1),(7,'Nhóm Chuyên Ngành - CN3',6,1),(8,'Nhóm Đại Cương',2,6);
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
INSERT INTO `hoc_phan_tu_chon_hoc_phan` VALUES (1,'CT126'),(1,'CT127'),(1,'CT479'),(1,'CT224'),(1,'CT274'),(2,'CT228'),(2,'CT229'),(2,'CT222'),(2,'CT344'),(2,'CT232'),(3,'CT207'),(3,'CT231'),(3,'CT230'),(3,'CT233'),(3,'CT482'),(1,'CT121'),(1,'CT225'),(4,'TC001'),(4,'TC002'),(4,'TC004'),(4,'TC005'),(4,'TC006'),(4,'TC007'),(4,'TC008'),(4,'TC009'),(4,'TC010'),(4,'TC011'),(4,'TC012'),(4,'TC013'),(4,'TC016'),(4,'TC017'),(4,'TC018'),(4,'TC019'),(4,'TC020'),(4,'TC021'),(4,'TC022'),(4,'TC023'),(4,'TC024'),(4,'TC025'),(4,'TC026'),(4,'TC027'),(4,'TC028'),(4,'TC029'),(4,'TC030'),(5,'CT211'),(5,'CT297'),(6,'CT449'),(6,'CT484'),(7,'CT522'),(7,'CT279'),(2,'CT212'),(8,'ML007'),(8,'XH028'),(8,'XH011'),(8,'XH012'),(8,'XH014'),(8,'KN001'),(8,'KN002');
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
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ke_hoach_hoc_tap`
--

LOCK TABLES `ke_hoach_hoc_tap` WRITE;
/*!40000 ALTER TABLE `ke_hoach_hoc_tap` DISABLE KEYS */;
INSERT INTO `ke_hoach_hoc_tap` VALUES (1,_binary '\0',1,'QP011','B2410945'),(2,_binary '\0',1,'QP012','B2410945'),(3,_binary '\0',1,'QP013','B2410945'),(5,_binary '\0',1,'CT100','B2410945'),(9,_binary '\0',1,'TN010','B2410945'),(19,_binary '\0',1,'QP010','B2448743'),(20,_binary '\0',1,'QP011','B2448743'),(21,_binary '\0',1,'QP012','B2448743'),(22,_binary '\0',1,'QP013','B2448743'),(23,_binary '\0',1,'CT100','B2448743'),(24,_binary '\0',1,'TN010','B2448743'),(25,_binary '\0',2,'CT101','B2448743'),(26,_binary '\0',2,'CT200','B2448743'),(27,_binary '\0',2,'ML007','B2448743'),(28,_binary '\0',2,'ML014','B2448743'),(29,_binary '\0',2,'TN001','B2448743'),(30,_binary '\0',2,'XH023','B2448743'),(31,_binary '\0',4,'CT172','B2448743'),(32,_binary '\0',4,'CT173','B2448743'),(33,_binary '\0',4,'ML016','B2448743'),(34,_binary '\0',4,'TN002','B2448743'),(35,_binary '\0',4,'XH024','B2448743'),(44,_binary '\0',4,'ML018','B2448743'),(45,_binary '\0',4,'TN012','B2448743'),(46,_binary '\0',4,'XH025','B2448743'),(47,_binary '\0',5,'CT177','B2448743'),(48,_binary '\0',5,'CT178','B2448743'),(49,_binary '\0',5,'KL001','B2448743'),(50,_binary '\0',4,'TC005','B2448743'),(51,_binary '\0',5,'TC006','B2448743'),(52,_binary '\0',7,'TC020','B2448743'),(53,_binary '\0',7,'CT112','B2448743'),(54,_binary '\0',7,'CT174','B2448743'),(55,_binary '\0',7,'CT175','B2448743'),(56,_binary '\0',7,'CT176','B2448743'),(57,_binary '\0',7,'CT180','B2448743'),(58,_binary '\0',7,'ML019','B2448743'),(59,_binary '\0',8,'CT179','B2448743'),(60,_binary '\0',8,'CT182','B2448743'),(61,_binary '\0',8,'CT188','B2448743'),(62,_binary '\0',8,'CT190','B2448743'),(63,_binary '\0',8,'CT273','B2448743'),(64,_binary '\0',8,'CT296','B2448743'),(65,_binary '\0',8,'ML021','B2448743'),(66,_binary '\0',10,'CT271','B2448743'),(67,_binary '\0',10,'CT275','B2448743'),(68,_binary '\0',10,'CT293','B2448743'),(69,_binary '\0',10,'CT294','B2448743'),(70,_binary '\0',10,'CT300','B2448743'),(71,_binary '\0',10,'CT467','B2448743'),(72,_binary '\0',11,'CT522','B2448743'),(73,_binary '\0',11,'CT279','B2448743'),(74,_binary '\0',10,'CT466','B2448743'),(75,_binary '\0',12,'CT471','B2448743'),(76,_binary '\0',13,'CT550','B2448743'),(86,_binary '\0',2,'CT101','B2410945'),(87,_binary '\0',2,'CT172','B2410945'),(88,_binary '\0',2,'CT200','B2410945'),(90,_binary '\0',2,'ML014','B2410945'),(91,_binary '\0',2,'XH023','B2410945'),(92,_binary '\0',2,'XH011','B2410945'),(93,_binary '\0',4,'CT173','B2410945'),(94,_binary '\0',4,'ML016','B2410945'),(95,_binary '\0',4,'TN001','B2410945'),(96,_binary '\0',4,'TN012','B2410945'),(97,_binary '\0',4,'XH024','B2410945'),(98,_binary '\0',5,'CT177','B2410945'),(99,_binary '\0',5,'CT178','B2410945'),(100,_binary '\0',5,'KL001','B2410945'),(101,_binary '\0',5,'ML019','B2410945'),(102,_binary '\0',5,'TN002','B2410945'),(103,_binary '\0',5,'XH025','B2410945'),(104,_binary '\0',7,'CT112','B2410945'),(105,_binary '\0',7,'CT174','B2410945'),(106,_binary '\0',7,'CT175','B2410945'),(107,_binary '\0',7,'CT176','B2410945'),(108,_binary '\0',7,'CT180','B2410945'),(109,_binary '\0',7,'ML021','B2410945'),(110,_binary '\0',8,'CT179','B2410945'),(111,_binary '\0',8,'CT182','B2410945'),(112,_binary '\0',8,'CT188','B2410945'),(113,_binary '\0',8,'CT190','B2410945'),(114,_binary '\0',8,'CT296','B2410945'),(115,_binary '\0',8,'CT335','B2410945'),(116,_binary '\0',10,'CT224','B2410945'),(117,_binary '\0',10,'CT225','B2410945'),(118,_binary '\0',10,'CT211','B2410945'),(119,_binary '\0',10,'CT221','B2410945'),(120,_binary '\0',10,'CT127','B2410945'),(121,_binary '\0',10,'CT428','B2410945'),(122,_binary '\0',10,'CT226','B2410945'),(123,_binary '\0',11,'CT222','B2410945'),(124,_binary '\0',11,'CT212','B2410945'),(125,_binary '\0',11,'CT232','B2410945'),(126,_binary '\0',11,'CT439','B2410945'),(127,_binary '\0',12,'CT476','B2410945'),(128,_binary '\0',13,'CT555','B2410945'),(129,_binary '\0',4,'TC028','B2410945'),(130,_binary '\0',5,'TC030','B2410945'),(131,_binary '\0',7,'TC029','B2410945'),(132,_binary '\0',1,'QP010','B2410945'),(133,_binary '\0',1,'QP011','B2462027'),(134,_binary '\0',1,'QP012','B2462027'),(135,_binary '\0',1,'QP013','B2462027'),(136,_binary '\0',1,'CT100','B2462027'),(137,_binary '\0',1,'TN010','B2462027'),(138,_binary '\0',2,'CT101','B2462027'),(139,_binary '\0',2,'CT172','B2462027'),(140,_binary '\0',2,'CT200','B2462027'),(141,_binary '\0',2,'ML014','B2462027'),(142,_binary '\0',2,'XH023','B2462027'),(143,_binary '\0',2,'XH011','B2462027'),(144,_binary '\0',4,'CT173','B2462027'),(145,_binary '\0',4,'ML016','B2462027'),(146,_binary '\0',4,'TN001','B2462027'),(147,_binary '\0',4,'TN012','B2462027'),(148,_binary '\0',4,'XH024','B2462027'),(149,_binary '\0',5,'CT177','B2462027'),(150,_binary '\0',5,'CT178','B2462027'),(151,_binary '\0',5,'KL001','B2462027'),(152,_binary '\0',5,'ML019','B2462027'),(153,_binary '\0',5,'TN002','B2462027'),(154,_binary '\0',5,'XH025','B2462027'),(155,_binary '\0',7,'CT112','B2462027'),(156,_binary '\0',7,'CT174','B2462027'),(157,_binary '\0',7,'CT175','B2462027'),(158,_binary '\0',7,'CT176','B2462027'),(159,_binary '\0',7,'CT180','B2462027'),(160,_binary '\0',7,'ML021','B2462027'),(161,_binary '\0',8,'CT179','B2462027'),(162,_binary '\0',8,'CT182','B2462027'),(163,_binary '\0',8,'CT188','B2462027'),(164,_binary '\0',8,'CT190','B2462027'),(165,_binary '\0',8,'CT296','B2462027'),(166,_binary '\0',8,'CT335','B2462027'),(167,_binary '\0',10,'CT224','B2462027'),(168,_binary '\0',10,'CT225','B2462027'),(169,_binary '\0',10,'CT211','B2462027'),(170,_binary '\0',10,'CT221','B2462027'),(171,_binary '\0',10,'CT127','B2462027'),(172,_binary '\0',10,'CT428','B2462027'),(173,_binary '\0',10,'CT226','B2462027'),(174,_binary '\0',11,'CT222','B2462027'),(175,_binary '\0',11,'CT212','B2462027'),(176,_binary '\0',11,'CT232','B2462027'),(177,_binary '\0',11,'CT439','B2462027'),(178,_binary '\0',12,'CT476','B2462027'),(179,_binary '\0',13,'CT555','B2462027'),(180,_binary '\0',4,'TC028','B2462027'),(181,_binary '\0',5,'TC030','B2462027'),(182,_binary '\0',7,'TC029','B2462027'),(183,_binary '\0',1,'QP010','B2462027'),(184,_binary '\0',2,'CT172','B2439130'),(185,_binary '\0',4,'CT173','B2439130');
/*!40000 ALTER TABLE `ke_hoach_hoc_tap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ke_hoach_hoc_tap_mau`
--

DROP TABLE IF EXISTS `ke_hoach_hoc_tap_mau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ke_hoach_hoc_tap_mau` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `khoa_hoc` varchar(255) DEFAULT NULL,
  `ma_hoc_ky` bigint DEFAULT NULL,
  `ma_hoc_phan` varchar(255) DEFAULT NULL,
  `ma_nganh` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=973 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ke_hoach_hoc_tap_mau`
--

LOCK TABLES `ke_hoach_hoc_tap_mau` WRITE;
/*!40000 ALTER TABLE `ke_hoach_hoc_tap_mau` DISABLE KEYS */;
INSERT INTO `ke_hoach_hoc_tap_mau` VALUES (50,'K50',10,'QP011E',8),(51,'K50',10,'QP012',8),(52,'K50',10,'QP013',8),(53,'K50',10,'CT100',8),(54,'K50',10,'CT200',8),(55,'K50',11,'TC100',8),(56,'K50',11,'XH023',8),(57,'K50',11,'FL001',8),(58,'K50',11,'ML014',8),(59,'K50',11,'CT101',8),(60,'K50',11,'KL001E',8),(61,'K50',12,'TC100',8),(62,'K50',12,'XH024',8),(63,'K50',12,'FL002',8),(64,'K50',12,'ML016',8),(65,'K50',12,'TN010',8),(66,'K50',12,'CT177',8),(67,'K50',13,'TC100',8),(68,'K50',13,'XH025',8),(69,'K50',13,'FL003',8),(70,'K50',13,'ML018',8),(71,'K50',13,'CT172',8),(72,'K50',13,'CT175',8),(73,'K50',14,'ML019',8),(74,'K50',14,'TN001',8),(75,'K50',14,'TN012',8),(76,'K50',14,'CT173',8),(77,'K50',14,'ML007',8),(78,'K50',14,'XH028',8),(79,'K50',14,'XH011',8),(80,'K50',14,'XH012',8),(81,'K50',14,'XH014',8),(82,'K50',14,'KN001E',8),(83,'K50',14,'KN002E',8),(84,'K50',16,'ML021',8),(85,'K50',16,'TN002',8),(86,'K50',16,'CT176',8),(87,'K50',16,'CT174',8),(88,'K50',17,'CT188',8),(89,'K50',17,'CT178',8),(90,'K50',17,'CT180',8),(91,'K50',17,'CT182',8),(92,'K50',18,'CT190',8),(93,'K50',18,'CT113',8),(94,'K50',18,'CT296',8),(95,'K50',18,'CT112',8),(96,'K50',18,'CT223',8),(97,'K50',19,'CT242',8),(98,'K50',19,'CT240',8),(99,'K50',19,'CT241',8),(100,'K50',19,'CT179',8),(101,'K50',20,'CT239E',8),(102,'K50',20,'CT244',8),(103,'K50',20,'CT243',8),(104,'K50',20,'CT246',8),(105,'K50',20,'CT276',8),(106,'K50',20,'CT449',8),(107,'K50',20,'CT483',8),(108,'K50',21,'CT250E',8),(109,'K50',21,'CT487',8),(110,'K50',21,'CT287',8),(111,'K50',21,'CT189',8),(112,'K50',21,'CT295',8),(113,'K50',21,'CT460',8),(114,'K50',22,'CT446',8),(115,'K50',22,'CT457',8),(116,'K50',22,'CT288',8),(117,'K50',22,'CT202',8),(118,'K50',22,'CT254',8),(119,'K50',22,'CT207',8),(120,'K50',22,'CT233',8),(121,'K50',22,'CT258',8),(122,'K50',22,'CT205',8),(123,'K50',22,'CT255',8),(124,'K50',22,'CT505E',8),(125,'K50',22,'CT553E',8),(126,'K50',23,'CT458E',8),(127,'K50',10,'QP011E',2),(128,'K50',10,'QP012',2),(129,'K50',10,'QP013',2),(130,'K50',10,'CT100',2),(131,'K50',10,'CT200',2),(132,'K50',11,'TC100',2),(133,'K50',11,'XH023',2),(134,'K50',11,'FL001',2),(135,'K50',11,'ML014',2),(136,'K50',11,'CT101',2),(137,'K50',11,'KL001E',2),(138,'K50',12,'TC100',2),(139,'K50',12,'XH024',2),(140,'K50',12,'FL002',2),(141,'K50',12,'ML016',2),(142,'K50',12,'TN010',2),(143,'K50',12,'CT177',2),(144,'K50',13,'TC100',2),(145,'K50',13,'XH025',2),(146,'K50',13,'FL003',2),(147,'K50',13,'ML018',2),(148,'K50',13,'CT172',2),(149,'K50',13,'CT175',2),(150,'K50',14,'ML019',2),(151,'K50',14,'TN001',2),(152,'K50',14,'TN012',2),(153,'K50',14,'CT173',2),(154,'K50',14,'ML007',2),(155,'K50',14,'XH028',2),(156,'K50',14,'XH011',2),(157,'K50',14,'XH012',2),(158,'K50',14,'XH014',2),(159,'K50',14,'KN001E',2),(160,'K50',14,'KN002E',2),(161,'K50',16,'ML021',2),(162,'K50',16,'TN002',2),(163,'K50',16,'CT176',2),(164,'K50',16,'CT174',2),(165,'K50',17,'CT188',2),(166,'K50',17,'CT178',2),(167,'K50',17,'CT180',2),(168,'K50',17,'CT182',2),(169,'K50',18,'CT190',2),(170,'K50',18,'CT113',2),(171,'K50',18,'CT296',2),(172,'K50',18,'CT112',2),(173,'K50',18,'CT223',2),(174,'K50',19,'CT242',2),(175,'K50',19,'CT240',2),(176,'K50',19,'CT241',2),(177,'K50',19,'CT179',2),(178,'K50',20,'CT239E',2),(179,'K50',20,'CT244',2),(180,'K50',20,'CT243',2),(181,'K50',20,'CT246',2),(182,'K50',20,'CT276',2),(183,'K50',20,'CT449',2),(184,'K50',20,'CT483',2),(185,'K50',21,'CT250E',2),(186,'K50',21,'CT487',2),(187,'K50',21,'CT287',2),(188,'K50',21,'CT189',2),(189,'K50',21,'CT295',2),(190,'K50',21,'CT460',2),(191,'K50',22,'CT446',2),(192,'K50',22,'CT457',2),(193,'K50',22,'CT288',2),(194,'K50',22,'CT202',2),(195,'K50',22,'CT254',2),(196,'K50',22,'CT207',2),(197,'K50',22,'CT233',2),(198,'K50',22,'CT258',2),(199,'K50',22,'CT205',2),(200,'K50',22,'CT255',2),(201,'K50',22,'CT505E',2),(202,'K50',22,'CT553E',2),(203,'K50',23,'CT458E',2),(380,'K47',1,'QP011',7),(381,'K47',1,'QP012',7),(382,'K47',1,'QP013',7),(383,'K47',1,'TN010',7),(384,'K47',1,'CT100',7),(385,'K47',2,'ML007',7),(386,'K47',2,'ML014',7),(387,'K47',2,'CT101',7),(388,'K47',2,'TN001',7),(389,'K47',2,'CT200',7),(390,'K47',2,'XH023',7),(391,'K47',4,'CT172',7),(392,'K47',4,'CT173',7),(393,'K47',4,'ML016',7),(394,'K47',4,'TC100',7),(395,'K47',4,'TN002',7),(396,'K47',4,'XH024',7),(397,'K47',5,'ML018',7),(398,'K47',5,'TC100',7),(399,'K47',5,'KL001',7),(400,'K47',5,'CT177',7),(401,'K47',5,'CT178',7),(402,'K47',5,'XH025',7),(403,'K47',5,'TN012',7),(404,'K47',7,'ML019',7),(405,'K47',7,'TC100',7),(406,'K47',7,'CT175',7),(407,'K47',7,'CT174',7),(408,'K47',7,'CT180',7),(409,'K47',7,'CT112',7),(410,'K47',7,'CT176',7),(411,'K47',8,'ML021',7),(412,'K47',8,'CT296',7),(413,'K47',8,'CT182',7),(414,'K47',8,'CT179',7),(415,'K47',8,'CT188',7),(416,'K47',8,'CT190',7),(417,'K47',8,'CT273',7),(418,'K47',10,'CT271',7),(419,'K47',10,'CT294',7),(420,'K47',10,'CT467',7),(421,'K47',10,'CT293',7),(422,'K47',10,'CT275',7),(423,'K47',10,'CT300',7),(424,'K47',11,'CT223',7),(425,'K47',11,'CT222',7),(426,'K47',11,'CT211',7),(427,'K47',11,'CT297',7),(428,'K47',11,'CT449',7),(429,'K47',11,'CT484',7),(430,'K47',11,'CT522',7),(431,'K47',11,'CT279',7),(432,'K47',11,'CT466',7),(433,'K47',12,'CT471',7),(434,'K47',13,'CT550',7),(435,'K47',13,'CT501',7),(436,'K47',13,'CT478',7),(437,'K47',13,'CT283',7),(438,'K47',13,'CT233',7),(439,'K47',13,'CT482',7),(440,'K47',13,'CT210',7),(441,'K47',13,'CT219',7),(442,'K47',13,'CT312',7),(802,'K47',2,'ML014',6),(803,'K47',2,'TC100',6),(804,'K47',2,'CT101',6),(805,'K47',2,'CT172',6),(806,'K47',2,'CT200',6),(807,'K47',2,'XH023',6),(808,'K47',2,'FL001',6),(809,'K47',4,'ML007',6),(810,'K47',4,'XH028',6),(811,'K47',4,'XH011',6),(812,'K47',4,'XH012',6),(813,'K47',4,'XH014',6),(814,'K47',4,'KN001',6),(815,'K47',4,'KN002',6),(816,'K47',4,'CT173',6),(817,'K47',4,'ML016',6),(818,'K47',4,'ML018',6),(819,'K47',4,'TN001',6),(820,'K47',4,'TN012',6),(821,'K47',4,'XH024',6),(822,'K47',4,'FL002',6),(823,'K47',5,'ML019',6),(824,'K47',5,'TC100',6),(825,'K47',5,'KL001',6),(826,'K47',5,'CT177',6),(827,'K47',5,'CT178',6),(828,'K47',5,'XH025',6),(829,'K47',5,'FL003',6),(830,'K47',5,'TN002',6),(831,'K47',7,'ML021',6),(832,'K47',7,'TC100',6),(833,'K47',7,'CT175',6),(834,'K47',7,'CT174',6),(835,'K47',7,'CT180',6),(836,'K47',7,'CT112',6),(837,'K47',7,'CT176',6),(838,'K47',8,'CT296',6),(839,'K47',8,'CT182',6),(840,'K47',8,'CT179',6),(841,'K47',8,'CT188',6),(842,'K47',8,'CT190',6),(843,'K47',8,'CT335',6),(844,'K47',10,'CT428',6),(845,'K47',10,'CT221',6),(846,'K47',10,'CT211',6),(847,'K47',10,'CT226',6),(848,'K47',10,'CT126',6),(849,'K47',10,'CT127',6),(850,'K47',10,'CT124',6),(851,'K47',10,'CT121',6),(852,'K47',10,'CT224',6),(853,'K47',10,'CT225',6),(854,'K47',10,'CT274',6),(855,'K47',11,'CT212',6),(856,'K47',11,'CT439',6),(857,'K47',11,'CT227',6),(858,'K47',11,'CT228',6),(859,'K47',11,'CT229',6),(860,'K47',11,'CT222',6),(861,'K47',11,'CT344',6),(862,'K47',11,'CT232',6),(863,'K47',11,'CT207',6),(864,'K47',11,'CT230',6),(865,'K47',11,'CT231',6),(866,'K47',11,'CT233',6),(867,'K47',11,'CT482',6),(868,'K47',12,'CT476',6),(869,'K47',13,'CT555',6),(870,'K47',13,'CT507',6),(871,'K47',13,'CT338',6),(872,'K47',13,'CT272',6),(873,'K47',13,'CT234',6),(874,'K47',13,'CT223',6),(875,'K47',13,'CT235',6),(876,'K47',13,'CT205',6),(877,'K47',13,'CT237',6),(878,'K47',13,'CT251',6),(879,'K47',13,'CT206',6),(880,'K47',13,'CT238',6),(881,'K47',13,'CT332',6),(882,'K47',13,'CT202',6),(883,'K47',13,'CT273',6),(885,'K46',1,'QP011',6),(886,'K46',1,'QP012',6),(887,'K46',1,'QP013',6),(888,'K46',1,'TN010',6),(889,'K46',1,'CT100',6),(890,'K46',2,'ML014',6),(891,'K46',2,'TC100',6),(892,'K46',2,'CT101',6),(893,'K46',2,'CT172',6),(894,'K46',2,'CT200',6),(895,'K46',2,'XH023',6),(896,'K46',2,'FL001',6),(897,'K46',4,'ML007',6),(898,'K46',4,'XH028',6),(899,'K46',4,'XH011',6),(900,'K46',4,'XH012',6),(901,'K46',4,'XH014',6),(902,'K46',4,'KN001',6),(903,'K46',4,'KN002',6),(904,'K46',4,'CT173',6),(905,'K46',4,'ML016',6),(906,'K46',4,'ML018',6),(907,'K46',4,'TN001',6),(908,'K46',4,'TN012',6),(909,'K46',4,'XH024',6),(910,'K46',4,'FL002',6),(911,'K46',5,'ML019',6),(912,'K46',5,'TC100',6),(913,'K46',5,'KL001',6),(914,'K46',5,'CT177',6),(915,'K46',5,'CT178',6),(916,'K46',5,'XH025',6),(917,'K46',5,'FL003',6),(918,'K46',5,'TN002',6),(919,'K46',7,'ML021',6),(920,'K46',7,'TC100',6),(921,'K46',7,'CT175',6),(922,'K46',7,'CT174',6),(923,'K46',7,'CT180',6),(924,'K46',7,'CT112',6),(925,'K46',7,'CT176',6),(926,'K46',8,'CT296',6),(927,'K46',8,'CT182',6),(928,'K46',8,'CT179',6),(929,'K46',8,'CT188',6),(930,'K46',8,'CT190',6),(931,'K46',8,'CT335',6),(932,'K46',10,'CT428',6),(933,'K46',10,'CT221',6),(934,'K46',10,'CT211',6),(935,'K46',10,'CT226',6),(936,'K46',10,'CT126',6),(937,'K46',10,'CT127',6),(938,'K46',10,'CT124',6),(939,'K46',10,'CT121',6),(940,'K46',10,'CT224',6),(941,'K46',10,'CT225',6),(942,'K46',10,'CT274',6),(943,'K46',11,'CT212',6),(944,'K46',11,'CT439',6),(945,'K46',11,'CT227',6),(946,'K46',11,'CT228',6),(947,'K46',11,'CT229',6),(948,'K46',11,'CT222',6),(949,'K46',11,'CT344',6),(950,'K46',11,'CT232',6),(951,'K46',11,'CT207',6),(952,'K46',11,'CT230',6),(953,'K46',11,'CT231',6),(954,'K46',11,'CT233',6),(955,'K46',11,'CT482',6),(956,'K46',12,'CT476',6),(957,'K46',13,'CT555',6),(958,'K46',13,'CT507',6),(959,'K46',13,'CT338',6),(960,'K46',13,'CT272',6),(961,'K46',13,'CT234',6),(962,'K46',13,'CT223',6),(963,'K46',13,'CT235',6),(964,'K46',13,'CT205',6),(965,'K46',13,'CT237',6),(966,'K46',13,'CT251',6),(967,'K46',13,'CT206',6),(968,'K46',13,'CT238',6),(969,'K46',13,'CT332',6),(970,'K46',13,'CT202',6),(971,'K46',13,'CT273',6),(972,'K46',1,'QP010',6);
/*!40000 ALTER TABLE `ke_hoach_hoc_tap_mau` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ket_qua_hoc_tap`
--

LOCK TABLES `ket_qua_hoc_tap` WRITE;
/*!40000 ALTER TABLE `ket_qua_hoc_tap` DISABLE KEYS */;
INSERT INTO `ket_qua_hoc_tap` VALUES (1,'B+',8.5,_binary '',1,'QP010',1,'B2410945',2),(2,'A',9,_binary '',1,'QP011',2,'B2410945',2),(3,'B',7.5,_binary '',1,'QP012',3,'B2410945',2),(4,'A',8.8,_binary '',1,'QP013',4,'B2410945',2),(5,'C+',6.5,_binary '\0',1,'TN010',5,'B2410945',3),(6,'B',7,_binary '\0',1,'CT100',1,'B2410945',2),(7,'B',7.5,_binary '\0',2,'ML014',2,'B2410945',3),(8,'A',9.2,_binary '\0',2,'TC100',3,'B2410945',1),(9,'B+',8,_binary '\0',2,'CT101',4,'B2410945',4),(10,'B',7,_binary '\0',2,'CT172',5,'B2410945',4),(11,'A',8.7,_binary '\0',2,'CT200',1,'B2410945',4),(12,'C+',6.5,_binary '',2,'XH023',2,'B2410945',4),(13,'B',7.2,_binary '\0',2,'FL001',3,'B2410945',4),(14,'B',7,_binary '\0',4,'ML007',4,'B2410945',2),(15,'B+',8,_binary '',4,'XH028',5,'B2410945',2),(16,'B',7.2,_binary '\0',4,'XH011',1,'B2410945',2),(17,'C+',6.5,_binary '\0',4,'XH012',2,'B2410945',2),(18,'B',7.5,_binary '\0',4,'XH014',3,'B2410945',2),(19,'A',8.6,_binary '\0',4,'KN001',4,'B2410945',2),(20,'A',9,_binary '\0',4,'KN002',5,'B2410945',2),(21,'B+',8.3,_binary '\0',4,'TN012',1,'B2410945',4),(22,'B',7,_binary '\0',4,'TN001',2,'B2410945',3),(23,'B',7.4,_binary '\0',4,'ML016',3,'B2410945',2),(24,'A',8.8,_binary '\0',4,'CT190',4,'B2410945',2),(25,'B+',8.1,_binary '\0',4,'CT177',5,'B2410945',3),(26,'B',7.3,_binary '',4,'XH024',1,'B2410945',3),(27,'B',7.1,_binary '\0',4,'FL002',2,'B2410945',3),(28,'B',7.2,_binary '\0',5,'ML018',3,'B2410945',2),(29,'A',8.5,_binary '\0',5,'TC100',4,'B2410945',1),(30,'A',9,_binary '',1,'QP010',1,'BX2400001',2),(31,'B',7,_binary '',1,'CT100',5,'BX2400001',2),(32,'F',3,_binary '\0',2,'CT101',1,'BX2400001',4),(33,'C',5.5,_binary '',2,'ML014',3,'BX2400001',3),(34,'A',10,_binary '',1,'QP010',1,'BX2400003',2),(35,'A',9.5,_binary '',1,'QP011',2,'BX2400003',2),(36,'B+',8.4,_binary '',2,'CT101',1,'BX2400003',4),(37,'A',8.8,_binary '',2,'CT172',2,'BX2400003',4);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES (1,'Mạng máy tính và Truyền thông dữ liệu'),(2,'An toàn Thông tin'),(4,'Hệ thống Thông tin'),(5,'Khoa học Máy tính'),(6,'Kỹ thuật Phần mềm'),(7,'Truyền thông Đa phương tiện'),(8,'Công Nghệ Thông Tin');
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
  `si_so` bigint DEFAULT NULL,
  `si_so_con` bigint DEFAULT NULL,
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
INSERT INTO `lop` VALUES ('AT24T9A9',NULL,'An toàn Thông tin A6',5,40,20),('AT24T9B1',NULL,'An toàn Thông tin B4',5,40,20),('AT24T9C6',NULL,'An toàn Thông tin C2',5,40,20),('CN24T9A7',NULL,'Công Nghệ Thông Tin A7',7,40,20),('CN24T9B1',NULL,'Công Nghệ Thông Tin B4',7,40,20),('CN24T9C8',NULL,'Công Nghệ Thông Tin C7',7,40,0),('DI24T9A1',NULL,'Mạng máy tính và Truyền thông dữ liệu A1',6,40,21),('DI24T9A9',NULL,'Mạng máy tính và Truyền thông dữ liệu A4',6,40,20),('DI24T9B8',NULL,'Mạng máy tính và Truyền thông dữ liệu B1',6,40,20),('DI24T9C8',NULL,'Mạng máy tính và Truyền thông dữ liệu C1',6,40,20),('DI24VIRTUAL1',NULL,'Mạng máy tính Lớp Ảo 1',6,40,2),('HT24T9A9',NULL,'Hệ thống Thông tin A1',4,40,20),('HT24T9B6',NULL,'Hệ thống Thông tin B7',4,40,20),('HT24T9C8',NULL,'Hệ thống Thông tin C2',4,40,20),('KH24T9A2',NULL,'Khoa học Máy tính A6',3,40,20),('KH24T9B5',NULL,'Khoa học Máy tính B6',3,40,20),('KH24T9C4',NULL,'Khoa học Máy tính C2',3,40,20),('KT24T9A8',NULL,'Kỹ thuật Phần mềm A9',2,40,20),('KT24T9B9',NULL,'Kỹ thuật Phần mềm B1',2,40,20),('KT24T9C3',NULL,'Kỹ thuật Phần mềm C4',2,40,20),('KT24VIRTUAL1',NULL,'Kỹ thuật Phần mềm Lớp Ảo 1',2,40,2),('TT24T9A9',NULL,'Truyền thông Đa phương tiện A8',1,40,20),('TT24T9B6',NULL,'Truyền thông Đa phương tiện B4',1,40,20),('TT24T9C3',NULL,'Truyền thông Đa phương tiện C2',1,40,20);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nam_hoc`
--

LOCK TABLES `nam_hoc` WRITE;
/*!40000 ALTER TABLE `nam_hoc` DISABLE KEYS */;
INSERT INTO `nam_hoc` VALUES (1,'2021','2022'),(2,'2022','2023'),(3,'2023','2024'),(4,'2024','2025'),(5,'2025','2026'),(6,'2026','2027'),(7,'2027','2028'),(8,'2028','2029'),(9,'2029','2030');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganh`
--

LOCK TABLES `nganh` WRITE;
/*!40000 ALTER TABLE `nganh` DISABLE KEYS */;
INSERT INTO `nganh` VALUES (1,'Truyền thông Đa phương tiện',7),(2,'Kỹ thuật Phần mềm',6),(3,'Khoa học Máy tính',5),(4,'Hệ thống Thông tin',4),(5,'An toàn Thông tin',2),(6,'Mạng máy tính và Truyền thông dữ liệu',1),(7,'Công Nghệ Thông Tin',8);
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
INSERT INTO `sinh_vien` VALUES ('B2410945','K47','DI24T9A1');
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
INSERT INTO `user` VALUES ('B2410945','nguyenluong@gmail.com',_binary '\0','Nguyen Hải Lương','2003-11-04','$2a$10$3PINS/5.2Pnpkm5In0w5a./olfUp.EAjqG6haeblWAUOOxztKAEuK'),('C2110945','luong@admin.com',_binary '','Nguyễn Hải Lương','2003-11-04','$2a$10$3PINS/5.2Pnpkm5In0w5a./olfUp.EAjqG6haeblWAUOOxztKAEuK');
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
INSERT INTO `user_roles` VALUES ('C2110945','GIANGVIEN'),('B2410945','SINHVIEN');
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

-- Dump completed on 2025-07-13  1:19:02
