<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210410084147 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tags_challenge DROP FOREIGN KEY FK_B308B70DCBC6E69D');
        $this->addSql('DROP INDEX IDX_B308B70DCBC6E69D ON tags_challenge');
        $this->addSql('ALTER TABLE tags_challenge DROP tags_challenge_id');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tags_challenge ADD tags_challenge_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE tags_challenge ADD CONSTRAINT FK_B308B70DCBC6E69D FOREIGN KEY (tags_challenge_id) REFERENCES tags_challenge (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('CREATE INDEX IDX_B308B70DCBC6E69D ON tags_challenge (tags_challenge_id)');
    }
}
