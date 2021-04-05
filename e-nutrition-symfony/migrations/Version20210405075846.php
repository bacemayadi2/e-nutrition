<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210405075846 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE proportion (id INT AUTO_INCREMENT NOT NULL, patient_id INT DEFAULT NULL, aliment_id INT NOT NULL, poid DOUBLE PRECISION NOT NULL, date DATETIME NOT NULL, INDEX IDX_BFB196F26B899279 (patient_id), INDEX IDX_BFB196F2415B9F11 (aliment_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE proportion ADD CONSTRAINT FK_BFB196F26B899279 FOREIGN KEY (patient_id) REFERENCES patient (id)');
        $this->addSql('ALTER TABLE proportion ADD CONSTRAINT FK_BFB196F2415B9F11 FOREIGN KEY (aliment_id) REFERENCES aliment (id)');
        $this->addSql('ALTER TABLE tag_utilisateur ADD is_photo_de_profile TINYINT(1) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE proportion');
        $this->addSql('ALTER TABLE tag_utilisateur DROP is_photo_de_profile');
    }
}
