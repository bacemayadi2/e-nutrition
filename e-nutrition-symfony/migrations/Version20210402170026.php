<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210402170026 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tag_blog_post ADD blog_post_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE tag_blog_post ADD CONSTRAINT FK_AB6DDA3AA77FBEAF FOREIGN KEY (blog_post_id) REFERENCES blog_post (id)');
        $this->addSql('CREATE INDEX IDX_AB6DDA3AA77FBEAF ON tag_blog_post (blog_post_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tag_blog_post DROP FOREIGN KEY FK_AB6DDA3AA77FBEAF');
        $this->addSql('DROP INDEX IDX_AB6DDA3AA77FBEAF ON tag_blog_post');
        $this->addSql('ALTER TABLE tag_blog_post DROP blog_post_id');
    }
}
