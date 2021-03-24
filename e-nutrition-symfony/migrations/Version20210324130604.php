<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210324130604 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE evaluation ADD nutritionniste_id INT DEFAULT NULL, ADD patient_id INT DEFAULT NULL, ADD created_at DATETIME NOT NULL');
        $this->addSql('ALTER TABLE evaluation ADD CONSTRAINT FK_1323A575279DA68A FOREIGN KEY (nutritionniste_id) REFERENCES nutritionniste (id)');
        $this->addSql('ALTER TABLE evaluation ADD CONSTRAINT FK_1323A5756B899279 FOREIGN KEY (patient_id) REFERENCES patient (id)');
        $this->addSql('CREATE INDEX IDX_1323A575279DA68A ON evaluation (nutritionniste_id)');
        $this->addSql('CREATE INDEX IDX_1323A5756B899279 ON evaluation (patient_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE evaluation DROP FOREIGN KEY FK_1323A575279DA68A');
        $this->addSql('ALTER TABLE evaluation DROP FOREIGN KEY FK_1323A5756B899279');
        $this->addSql('DROP INDEX IDX_1323A575279DA68A ON evaluation');
        $this->addSql('DROP INDEX IDX_1323A5756B899279 ON evaluation');
        $this->addSql('ALTER TABLE evaluation DROP nutritionniste_id, DROP patient_id, DROP created_at');
    }
}
