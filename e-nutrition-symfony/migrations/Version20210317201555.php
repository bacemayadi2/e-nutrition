<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210317201555 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tag_success_story ADD success_story_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE tag_success_story ADD CONSTRAINT FK_90EC4948C4903472 FOREIGN KEY (success_story_id) REFERENCES success_story (id)');
        $this->addSql('CREATE INDEX IDX_90EC4948C4903472 ON tag_success_story (success_story_id)');
        $this->addSql('ALTER TABLE utilisateur ADD password VARCHAR(255) NOT NULL, ADD is_verified TINYINT(1) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tag_fiche_consultation ADD CONSTRAINT FK_9D0572AE4F3CB4A0 FOREIGN KEY (fiche_consultation_id) REFERENCES fiche_consultation (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('ALTER TABLE tag_fiche_consultation ADD CONSTRAINT FK_9D0572AEBF396750 FOREIGN KEY (id) REFERENCES tag (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE tag_success_story DROP FOREIGN KEY FK_90EC4948C4903472');
        $this->addSql('DROP INDEX IDX_90EC4948C4903472 ON tag_success_story');
        $this->addSql('ALTER TABLE tag_success_story DROP success_story_id');
        $this->addSql('ALTER TABLE utilisateur DROP password, DROP is_verified');
    }
}
