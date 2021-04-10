<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210410092052 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE challenge_tag ADD user_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE challenge_tag ADD CONSTRAINT FK_1AC21A8FA76ED395 FOREIGN KEY (user_id) REFERENCES utilisateur (id)');
        $this->addSql('CREATE INDEX IDX_1AC21A8FA76ED395 ON challenge_tag (user_id)');
        $this->addSql('ALTER TABLE patient DROP FOREIGN KEY FK_1ADAD7EB496FC0D0');
        $this->addSql('DROP INDEX IDX_1ADAD7EB496FC0D0 ON patient');
        $this->addSql('ALTER TABLE patient DROP challenge_tag_id');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE challenge_tag DROP FOREIGN KEY FK_1AC21A8FA76ED395');
        $this->addSql('DROP INDEX IDX_1AC21A8FA76ED395 ON challenge_tag');
        $this->addSql('ALTER TABLE challenge_tag DROP user_id');
        $this->addSql('ALTER TABLE patient ADD challenge_tag_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB496FC0D0 FOREIGN KEY (challenge_tag_id) REFERENCES challenge_tag (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('CREATE INDEX IDX_1ADAD7EB496FC0D0 ON patient (challenge_tag_id)');
    }
}
