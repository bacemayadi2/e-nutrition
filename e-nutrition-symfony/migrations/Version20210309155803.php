<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210309155803 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE contenu_multimedia (id INT AUTO_INCREMENT NOT NULL, updated_at DATETIME NOT NULL, description VARCHAR(1000) DEFAULT NULL, nom_file VARCHAR(1000) NOT NULL, dtype VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE image (id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE tag (id INT AUTO_INCREMENT NOT NULL, contenu_multimedia_id INT NOT NULL, dtype VARCHAR(255) NOT NULL, INDEX IDX_389B783C272E341 (contenu_multimedia_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE tag_nourriture (id INT NOT NULL, nourriture_id INT NOT NULL, INDEX IDX_D15C644298BD5834 (nourriture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE video (id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE image ADD CONSTRAINT FK_C53D045FBF396750 FOREIGN KEY (id) REFERENCES contenu_multimedia (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE tag ADD CONSTRAINT FK_389B783C272E341 FOREIGN KEY (contenu_multimedia_id) REFERENCES contenu_multimedia (id)');
        $this->addSql('ALTER TABLE tag_nourriture ADD CONSTRAINT FK_D15C644298BD5834 FOREIGN KEY (nourriture_id) REFERENCES nourriture (id)');
        $this->addSql('ALTER TABLE tag_nourriture ADD CONSTRAINT FK_D15C6442BF396750 FOREIGN KEY (id) REFERENCES tag (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE video ADD CONSTRAINT FK_7CC7DA2CBF396750 FOREIGN KEY (id) REFERENCES contenu_multimedia (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE image DROP FOREIGN KEY FK_C53D045FBF396750');
        $this->addSql('ALTER TABLE tag DROP FOREIGN KEY FK_389B783C272E341');
        $this->addSql('ALTER TABLE video DROP FOREIGN KEY FK_7CC7DA2CBF396750');
        $this->addSql('ALTER TABLE tag_nourriture DROP FOREIGN KEY FK_D15C6442BF396750');
        $this->addSql('DROP TABLE contenu_multimedia');
        $this->addSql('DROP TABLE image');
        $this->addSql('DROP TABLE tag');
        $this->addSql('DROP TABLE tag_nourriture');
        $this->addSql('DROP TABLE video');
    }
}
