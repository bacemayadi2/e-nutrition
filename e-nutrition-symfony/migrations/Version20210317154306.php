<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210317154306 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE tag_fiche_consultation (id INT NOT NULL, fiche_consultation_id INT DEFAULT NULL, INDEX IDX_9D0572AE4F3CB4A0 (fiche_consultation_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE tag_fiche_consultation ADD CONSTRAINT FK_9D0572AE4F3CB4A0 FOREIGN KEY (fiche_consultation_id) REFERENCES fiche_consultation (id)');
        $this->addSql('ALTER TABLE tag_fiche_consultation ADD CONSTRAINT FK_9D0572AEBF396750 FOREIGN KEY (id) REFERENCES tag (id) ON DELETE CASCADE');

    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE tag_fiche_consultation');

    }
}
