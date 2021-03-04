<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210304232544 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE etape_de_preparation ADD plat_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE etape_de_preparation ADD CONSTRAINT FK_35EC67BDD73DB560 FOREIGN KEY (plat_id) REFERENCES plat (id)');
        $this->addSql('CREATE INDEX IDX_35EC67BDD73DB560 ON etape_de_preparation (plat_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE etape_de_preparation DROP FOREIGN KEY FK_35EC67BDD73DB560');
        $this->addSql('DROP INDEX IDX_35EC67BDD73DB560 ON etape_de_preparation');
        $this->addSql('ALTER TABLE etape_de_preparation DROP plat_id');
    }
}
