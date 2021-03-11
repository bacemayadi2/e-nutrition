<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210311101958 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE tag_evaluation (id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE tag_evaluation ADD CONSTRAINT FK_B6382724BF396750 FOREIGN KEY (id) REFERENCES tag (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE composition DROP FOREIGN KEY FK_C7F43478D80C648');
        $this->addSql('DROP INDEX IDX_C7F43478D80C648 ON composition');
        $this->addSql('ALTER TABLE composition CHANGE aliments_id aliment_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE composition ADD CONSTRAINT FK_C7F4347415B9F11 FOREIGN KEY (aliment_id) REFERENCES aliment (id)');
        $this->addSql('CREATE INDEX IDX_C7F4347415B9F11 ON composition (aliment_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE tag_evaluation');
        $this->addSql('ALTER TABLE composition DROP FOREIGN KEY FK_C7F4347415B9F11');
        $this->addSql('DROP INDEX IDX_C7F4347415B9F11 ON composition');
        $this->addSql('ALTER TABLE composition CHANGE aliment_id aliments_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE composition ADD CONSTRAINT FK_C7F43478D80C648 FOREIGN KEY (aliments_id) REFERENCES aliment (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('CREATE INDEX IDX_C7F43478D80C648 ON composition (aliments_id)');
    }
}
