CREATE SCHEMA `searcher_refactor` DEFAULT CHARACTER SET utf8mb4 ;

CREATE TABLE `searcher_refactor`.`forward_indexes` (
    `docid` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `url` VARCHAR(200) NOT NULL,
    `content` LONGTEXT NOT NULL,
    PRIMARY KEY (`docid`))
COMMENT = '存放正排索引\ndocid -> 文档的完整信息';

CREATE TABLE `searcher_refactor`.`inverted_indexes` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `word` VARCHAR(100) NOT NULL,
    `docid` INT NOT NULL,
    `weight` INT NOT NULL,
    PRIMARY KEY (`id`))
COMMENT = '倒排索引\n通过 word -> [ { docid + weight }, { docid + weight }, ... ]';

-- 搜索的 SQL 大概这么去写
select docid from
`searcher_refactor`.`inverted_indexes`
where word = '我们要搜索的词'
order by weight desc
limit 20
offset 0;

select *
from `searcher_refactor`.`forward_indexes`
where docid in (1, 2, 3);

