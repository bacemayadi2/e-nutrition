<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210304183943 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE mesure DROP FOREIGN KEY FK_5F1B6E701D236AAA');
        $this->addSql('ALTER TABLE repas_aliment DROP FOREIGN KEY FK_D91FFC71D236AAA');
        $this->addSql('CREATE TABLE medicament (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, durée VARCHAR(255) NOT NULL, quantité VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE nutritionniste (id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE patient (id INT NOT NULL, style_de_vie VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE secretaire (id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE success_story (id INT AUTO_INCREMENT NOT NULL, titre VARCHAR(255) NOT NULL, text VARCHAR(10000) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE nutritionniste ADD CONSTRAINT FK_F02DE71CBF396750 FOREIGN KEY (id) REFERENCES utilisateur (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EBBF396750 FOREIGN KEY (id) REFERENCES utilisateur (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE secretaire ADD CONSTRAINT FK_7DB5C2D0BF396750 FOREIGN KEY (id) REFERENCES utilisateur (id) ON DELETE CASCADE');
        $this->addSql('DROP TABLE blog_post');
        $this->addSql('DROP TABLE repas');
        $this->addSql('DROP TABLE repas_aliment');
        $this->addSql('ALTER TABLE aliment DROP nom, DROP lipides, DROP glucides, DROP proteines, DROP poid, CHANGE id id INT NOT NULL');
        $this->addSql('ALTER TABLE aliment ADD CONSTRAINT FK_70FF972BBF396750 FOREIGN KEY (id) REFERENCES nourriture (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE mesure DROP FOREIGN KEY FK_5F1B6E703256915B');
        $this->addSql('DROP INDEX IDX_5F1B6E703256915B ON mesure');
        $this->addSql('DROP INDEX UNIQ_5F1B6E701D236AAA ON mesure');
        $this->addSql('ALTER TABLE mesure ADD date_mesure DATE NOT NULL, ADD poid DOUBLE PRECISION DEFAULT NULL, DROP repas_id, DROP relation_id, DROP poids, DROP imc, DROP calories_pris, CHANGE taille taille DOUBLE PRECISION DEFAULT NULL');
        $this->addSql('ALTER TABLE nourriture ADD dtype VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE utilisateur ADD dtype VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE blog_post (id INT AUTO_INCREMENT NOT NULL, title VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, body VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date DATETIME NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE repas (id INT NOT NULL, id_repas INT NOT NULL, quantite DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE repas_aliment (repas_id INT NOT NULL, aliment_id INT NOT NULL, INDEX IDX_D91FFC71D236AAA (repas_id), INDEX IDX_D91FFC7415B9F11 (aliment_id), PRIMARY KEY(repas_id, aliment_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE repas_aliment ADD CONSTRAINT FK_D91FFC71D236AAA FOREIGN KEY (repas_id) REFERENCES repas (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE repas_aliment ADD CONSTRAINT FK_D91FFC7415B9F11 FOREIGN KEY (aliment_id) REFERENCES aliment (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('DROP TABLE medicament');
        $this->addSql('DROP TABLE nutritionniste');
        $this->addSql('DROP TABLE patient');
        $this->addSql('DROP TABLE secretaire');
        $this->addSql('DROP TABLE success_story');
        $this->addSql('ALTER TABLE aliment DROP FOREIGN KEY FK_70FF972BBF396750');
        $this->addSql('ALTER TABLE aliment ADD nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, ADD lipides DOUBLE PRECISION NOT NULL, ADD glucides DOUBLE PRECISION NOT NULL, ADD proteines DOUBLE PRECISION NOT NULL, ADD poid DOUBLE PRECISION NOT NULL, CHANGE id id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE mesure ADD repas_id INT NOT NULL, ADD relation_id INT NOT NULL, ADD poids DOUBLE PRECISION NOT NULL, ADD imc DOUBLE PRECISION NOT NULL, ADD calories_pris DOUBLE PRECISION NOT NULL, DROP date_mesure, DROP poid, CHANGE taille taille DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE mesure ADD CONSTRAINT FK_5F1B6E701D236AAA FOREIGN KEY (repas_id) REFERENCES repas (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('ALTER TABLE mesure ADD CONSTRAINT FK_5F1B6E703256915B FOREIGN KEY (relation_id) REFERENCES utilisateur (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
        $this->addSql('CREATE INDEX IDX_5F1B6E703256915B ON mesure (relation_id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_5F1B6E701D236AAA ON mesure (repas_id)');
        $this->addSql('ALTER TABLE nourriture DROP dtype');
        $this->addSql('ALTER TABLE utilisateur DROP dtype');
    }
}
