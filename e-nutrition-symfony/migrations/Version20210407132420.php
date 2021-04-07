<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210407132420 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE tags_challenge (id INT AUTO_INCREMENT NOT NULL, tags_challenge_id INT DEFAULT NULL, INDEX IDX_B308B70DCBC6E69D (tags_challenge_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE tags_challenge ADD CONSTRAINT FK_B308B70DCBC6E69D FOREIGN KEY (tags_challenge_id) REFERENCES tags_challenge (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tags_challenge DROP FOREIGN KEY FK_B308B70DCBC6E69D');
        $this->addSql('DROP TABLE tags_challenge');
    }
}
