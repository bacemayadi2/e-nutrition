<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210404221330 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE nourriture ADD nutritionniste_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE nourriture ADD CONSTRAINT FK_7447E613279DA68A FOREIGN KEY (nutritionniste_id) REFERENCES nutritionniste (id)');
        $this->addSql('CREATE INDEX IDX_7447E613279DA68A ON nourriture (nutritionniste_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE nourriture DROP FOREIGN KEY FK_7447E613279DA68A');
        $this->addSql('DROP INDEX IDX_7447E613279DA68A ON nourriture');
        $this->addSql('ALTER TABLE nourriture DROP nutritionniste_id');
    }
}
