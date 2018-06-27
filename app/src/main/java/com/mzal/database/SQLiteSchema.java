package com.mzal.database;

public class SQLiteSchema {
    public static final class AutomaticLineTable {
        public static final String CREATE_QUERY = "CREATE TABLE `automated_line` (\n" +
                "  `id` varchar(255) DEFAULT NULL,\n" +
                "  `type_en` varchar(245) NOT NULL,\n" +
                "  `type_ru` varchar(200) DEFAULT NULL,\n" +
                "  `model_en` varchar(250) NOT NULL,\n" +
                "  `model_ru` varchar(200) NOT NULL,\n" +
                "  `url` varchar(255) NOT NULL,\n" +
                "  `manufacturer_en` varchar(255) NOT NULL,\n" +
                "  `manufacturer_ru` varchar(200) DEFAULT NULL,\n" +
                "  `country_en` varchar(255) NOT NULL,\n" +
                "  `country_ru` varchar(200) DEFAULT NULL,\n" +
                "  `CNC_en` varchar(245) NOT NULL,\n" +
                "  `CNC_ru` varchar(200) DEFAULT NULL,\n" +
                "  `CNC_full_en` varchar(200) NOT NULL,\n" +
                "  `CNC_full_ru` varchar(200) DEFAULT NULL,\n" +
                "  `machine_condition_en` varchar(200) NOT NULL,\n" +
                "  `machine_condition_ru` varchar(200) DEFAULT NULL,\n" +
                "  `machine_location_en` varchar(200) NOT NULL,\n" +
                "  `machine_location_ru` varchar(45) DEFAULT NULL,\n" +
                "  `year_of_manufacture` int(11) NOT NULL,\n" +
                "  `workpiece_en` varchar(245) NOT NULL,\n" +
                "  `workpiece_ru` varchar(245) DEFAULT NULL,\n" +
                "  `workpiece_weight` int(11) NOT NULL,\n" +
                "  `workpiece_photo1` varchar(200) NOT NULL,\n" +
                "  `workpiece_photo2` varchar(200) NOT NULL,\n" +
                "  `workpiece_description_en` varchar(200) NOT NULL,\n" +
                "  `workpiece_description_ru` varchar(245) DEFAULT NULL,\n" +
                "  `line_width` int(11) NOT NULL,\n" +
                "  `line_length` int(11) NOT NULL,\n" +
                "  `line_hight` int(11) DEFAULT NULL,\n" +
                "  `line_weight` int(11) NOT NULL,\n" +
                "  `num_of_working_staff` int(11) NOT NULL,\n" +
                "  `productivity` double DEFAULT NULL,\n" +
                "  `price` int(11) DEFAULT NULL,\n" +
                "  `photo1` varchar(245) DEFAULT NULL,\n" +
                "  `photo2` varchar(245) DEFAULT NULL,\n" +
                "  `photo3` varchar(245) DEFAULT NULL,\n" +
                "  `description_en` text,\n" +
                "  `description_ru` varchar(245) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    }

    public static final class LatheTable {
        public static final String CREATE_QUERY = "CREATE TABLE `lathe` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `productId` varchar(100) NOT NULL,\n" +
                "  `type` varchar(45) DEFAULT NULL,\n" +
                "  `typeurl` varchar(45) DEFAULT NULL,\n" +
                "  `model` varchar(45) DEFAULT NULL,\n" +
                "  `modelurl` varchar(45) DEFAULT NULL,\n" +
                "  `producer` varchar(45) DEFAULT NULL,\n" +
                "  `producingCountry` varchar(45) DEFAULT NULL,\n" +
                "  `systemCNC` varchar(145) DEFAULT NULL,\n" +
                "  `fullSystemCNC` varchar(255) DEFAULT NULL,\n" +
                "  `year1` int(11) DEFAULT NULL,\n" +
                "  `condition1` varchar(150) DEFAULT NULL,\n" +
                "  `machineLocation` varchar(45) DEFAULT NULL,\n" +
                "  `axisCount` int(11) DEFAULT NULL,\n" +
                "  `diameter_max` int(11) DEFAULT NULL,\n" +
                "  `diameter` int(11) DEFAULT NULL,\n" +
                "  `bardiameter` int(11) DEFAULT NULL,\n" +
                "  `length_max` int(11) DEFAULT NULL,\n" +
                "  `x` int(11) DEFAULT NULL,\n" +
                "  `y` int(11) DEFAULT NULL,\n" +
                "  `z` int(11) DEFAULT NULL,\n" +
                "  `spindleSpeed` int(11) DEFAULT NULL,\n" +
                "  `spindlepower` varchar(20) DEFAULT NULL,\n" +
                "  `spindlediameter` int(11) DEFAULT NULL,\n" +
                "  `subspindle` varchar(11) DEFAULT NULL,\n" +
                "  `subspindleSpeed` int(11) DEFAULT NULL,\n" +
                "  `vdi` varchar(45) DEFAULT NULL,\n" +
                "  `toolsall` varchar(10) DEFAULT NULL,\n" +
                "  `toolslive` varchar(10) DEFAULT NULL,\n" +
                "  `toolsnotlive` varchar(10) DEFAULT NULL,\n" +
                "  `tailstock` varchar(100) DEFAULT NULL,\n" +
                "  `accuracy` varchar(100) DEFAULT NULL,\n" +
                "  `accuracyAxisC` varchar(100) DEFAULT NULL,\n" +
                "  `spindletime` varchar(100) DEFAULT NULL,\n" +
                "  `machineRuntime` varchar(100) DEFAULT NULL,\n" +
                "  `price` int(11) DEFAULT NULL,\n" +
                "  `photo1` varchar(100) DEFAULT NULL,\n" +
                "  `photo2` varchar(100) DEFAULT NULL,\n" +
                "  `photo3` varchar(100) DEFAULT NULL,\n" +
                "  `photo4` varchar(100) DEFAULT NULL,\n" +
                "  `photo5` varchar(100) DEFAULT NULL,\n" +
                "  `descriptionen` text,\n" +
                "  `descriptionru` text,\n" +
                "  `video1` varchar(100) DEFAULT NULL,\n" +
                "  `video2` varchar(100) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    }
}
